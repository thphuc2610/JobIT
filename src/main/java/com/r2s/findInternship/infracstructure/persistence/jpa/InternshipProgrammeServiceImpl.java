package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.*;
import com.r2s.findInternship.domain.repository.*;
import com.r2s.findInternship.domain.service.*;

import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.persistence.mapper.InternshipProgrammeMapper;

@Service
@RequiredArgsConstructor
public class InternshipProgrammeServiceImpl implements InternshipProgrammeService {
    private final InternshipProgrammeRepository internshipProgrammeRepository;
    private final InternshipProgrammeMapper internshipProgrammeMapper;
    private final MessageSource messageSource;
    private final UniversityService universityService;
    private final StatusRepository statusRepository;
    private final InternshipMajorService internshipMajorService;
    private final InternshipPositionService internshipPositionService;
    private final InternshipScheduleService internshipScheduleService;
    private final FileService fileService;
    private final PositionRepository positionRepository;
    private final MajorRepository majorRepository;
    private final InternshipMajorRepository internshipMajorRepository;
    private final InternshipPositionRepository internshipPositionRepository;
    private final HttpServletRequest httpServletRequest;
    private final UserService userService;

    @Override // check
    public PaginationDTO findAllActive(int no, int limit) {
        Page<Object> page = internshipProgrammeRepository.findAllActive(PageRequest.of(no, limit))
                .map(internshipProgrammeMapper::toDTOShow);
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public InternshipProgrammeDTOShow create(InternshipProgrammeDTO internshipProgrammeDTO, MultipartFile file) {
        InternshipProgramme entity = internshipProgrammeMapper.toEntity(internshipProgrammeDTO);

        if (file != null && !file.isEmpty()) {
            fileService.uploadFile(file);
        }

        entity.setStatus(statusRepository.findById(Estatus.activeStatus)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Status not found with id: ", Estatus.activeStatus))));

        InternshipProgramme savedEntity = internshipProgrammeRepository.save(entity);

        Optional.ofNullable(internshipProgrammeDTO.getMajorDTOs())
                .ifPresent(majorDTOs -> majorDTOs.forEach(
                        majorDTO -> internshipMajorService.created(savedEntity, majorDTO)
                ));

        Optional.ofNullable(internshipProgrammeDTO.getPositionDTOs())
                .ifPresent(positionDTOs -> positionDTOs.forEach(
                        positionDTO -> internshipPositionService.created(savedEntity, positionDTO)
                ));

        // Optional.ofNullable(internshipProgrammeDTO.getScheduleDTOs())
        //         .ifPresent(scheduleDTOs -> scheduleDTOs.forEach(
        //                 scheduleDTO -> internshipScheduleService.created(savedEntity, scheduleDTO)
        //         ));

        return internshipProgrammeMapper.toDTOShow(savedEntity);
    }

    @Override
    public InternshipProgrammeDTOShow update(InternshipProgrammeDTO universityDemandDTO, MultipartFile file, Long id) {
        Long currentUserId = userService.getCurrentUserId();
        if (currentUserId == null) {
            throw new AccessDeniedExceptionV2();
        }

        InternshipProgramme existingEntity = internshipProgrammeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Internship Programme not found with id: ", id)));

        // Kiểm tra xem người đăng bài có phải là người thực hiện thay đổi
        if (!existingEntity.getCreatedBy().equals(currentUserId)) {
            throw new AccessDeniedExceptionV2();
        }

        if (file == null || file.isEmpty()) {
            throw new ValidationExceptionV2(Collections.singletonMap("File error", "File cannot be null or empty"));
        }

        internshipProgrammeMapper.updateEntityFromDTO(universityDemandDTO, existingEntity);
        existingEntity.setCreatedDate(new Date());

        if (universityDemandDTO.getPositionDTOs() != null) {
            List<Integer> newPositionIds = universityDemandDTO.getPositionDTOs().stream()
                    .map(PositionDTO::getId)
                    .collect(Collectors.toList());

            existingEntity.getInternshipPositions().removeIf(internshipPosition -> {
                Position position = internshipPosition.getPosition();
                return position == null || !newPositionIds.contains(position.getId());
            });

            for (PositionDTO positionDTO : universityDemandDTO.getPositionDTOs()) {
                Position position = positionRepository.findById(positionDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                Collections.singletonMap("Position not found with id: ", positionDTO.getId())));

                boolean exists = existingEntity.getInternshipPositions().stream()
                        .anyMatch(ip -> ip.getPosition() != null && ip.getPosition().getId() == (position.getId())); // Kiểm tra null cho Position

                if (!exists) {
                    InternshipPosition existingInternshipPosition = internshipPositionRepository.findByInternshipProgrammeAndPosition(existingEntity, position);
                    if (existingInternshipPosition == null) {
                        InternshipPosition internshipPosition = new InternshipPosition();
                        internshipPosition.setInternshipProgramme(existingEntity);
                        internshipPosition.setPosition(position);

                        internshipPositionRepository.save(internshipPosition);
                        existingEntity.getInternshipPositions().add(internshipPosition);
                    }
                }
            }
        }

        if (universityDemandDTO.getMajorDTOs() != null) {
            List<Integer> newMajorIds = universityDemandDTO.getMajorDTOs().stream()
                    .map(MajorDTO::getId)
                    .collect(Collectors.toList());

            existingEntity.getInternshipMajors().removeIf(internshipMajor -> {
                Major major = internshipMajor.getMajor();
                return major == null || !newMajorIds.contains(major.getId());
            });

            for (MajorDTO majorDTO : universityDemandDTO.getMajorDTOs()) {
                Major major = majorRepository.findById(majorDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                Collections.singletonMap("Major not found with id: ", majorDTO.getId())));

                boolean exists = existingEntity.getInternshipMajors().stream()
                        .anyMatch(im -> im.getMajor() != null && im.getMajor().getId() == (major.getId()));

                if (!exists) {
                    InternshipMajor existingInternshipMajor = internshipMajorRepository.findByInternshipProgrammeAndMajor(existingEntity, major);
                    if (existingInternshipMajor == null) {
                        InternshipMajor internshipMajor = new InternshipMajor();
                        internshipMajor.setInternshipProgramme(existingEntity);
                        internshipMajor.setMajor(major);

                        internshipMajorRepository.save(internshipMajor);
                        existingEntity.getInternshipMajors().add(internshipMajor);
                    }
                }
            }
        }
        return internshipProgrammeMapper.toDTOShow(internshipProgrammeRepository.save(existingEntity));
    }

    @Override
    public List<InternshipProgrammeDTOShow> findAll() {
        return this.internshipProgrammeRepository.findAll().stream()
                .map(this.internshipProgrammeMapper::toDTOShow)
                .collect(Collectors.toList());
    }

//    @Override
//    public PaginationDTO filter(InternshipProgrammeFilterDTO internshipProgrammeFilterDTO, int no, int limit) {
//        Page<InternshipProgrammeDTOShow> page = internshipProgrammeRepository
//                .findAll(InternshipSpecification.filter(internshipProgrammeFilterDTO), PageRequest.of(no, limit))
//                .map(internshipProgrammeMapper::toDTOShow);
//
//        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
//                page.getTotalPages(),
//                page.getTotalElements(), page.getSize(),
//                page.getNumber());
//    }

    @Override
    public PaginationDTO findAllByNameLike(String name, int no, int limit) {
        Page<InternshipProgrammeDTOShow> page = this.internshipProgrammeRepository
                .findAllByTitleLike(name, PageRequest.of(no, limit))
                .map(internshipProgrammeMapper::toDTOShow);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public InternshipProgrammeDTO readJson(String content, MultipartFile file) {
        InternshipProgrammeDTO demand = new InternshipProgrammeDTO();
        if (file != null)
            demand.setFile(file);
        else
            throw new InternalServerErrorException(this.messageSource.getMessage("error.FileNull", null, null));

        return demand;
    }

    @Override
    public InternshipProgrammeDTOShow findById(Long id) {
        return this.internshipProgrammeMapper.toDTOShow(this.internshipProgrammeRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with id: ", id))));
    }

    @Transactional
    public MessageResponse deleteById(Long id) {
        InternshipProgramme entity = this.internshipProgrammeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with id: ", id)));

        Long currentUser = userService.getCurrentUserId();

        if (!entity.getCreatedBy().equals(currentUser)) {
            throw new AccessDeniedExceptionV2();
        }

        internshipPositionRepository.deleteByInternshipProgramme(entity);
        internshipMajorRepository.deleteByInternshipProgramme(entity);
        internshipProgrammeRepository.delete(entity);

        return new MessageResponse(
                HttpServletResponse.SC_OK,
                "Deleted Internship Programme successfully",
                httpServletRequest.getServletPath()
        );
    }

    @Override
    public PaginationDTO findAllActiveByUniversityId(Long universityId, int no, int limit) {
        boolean isUniversity = this.universityService.existsById(universityId);
        if (!isUniversity)
            throw new ResourceNotFoundExceptionV2(
                    Collections.singletonMap("University not found with id: ", universityId));

        Page<Object> page = internshipProgrammeRepository
                .findAllActiveByUniversityId(universityId, PageRequest.of(no, limit))
                .map(internshipProgrammeMapper::toDTOShow);

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public InternshipProgrammeDTOShow updateStatus(Long id, boolean open) {
        InternshipProgramme internshipProgramme = internshipProgrammeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Internship Programme not found with id: ", id)));

        if (!internshipProgramme.getCreatedBy().equals(userService.getCurrentUserId())) {
            throw new AccessDeniedExceptionV2();
        }
        int statusId = open ? Estatus.activeStatus : Estatus.disableStatus;
        Status newStatus = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Status not found with id: ", statusId)));

        internshipProgramme.setStatus(newStatus);

        return internshipProgrammeMapper.toDTOShow(internshipProgrammeRepository.save(internshipProgramme));
    }
}
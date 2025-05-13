package com.r2s.findInternship.infracstructure.persistence.jpa.partner;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.application.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerProfileDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.University;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.InternshipProgrammeRepository;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.FileService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.InternshipProgrammeMapper;
import com.r2s.findInternship.infracstructure.persistence.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.domain.entity.Partner;
import com.r2s.findInternship.domain.repository.PartnerRepository;
import com.r2s.findInternship.domain.service.PartnerService;
import com.r2s.findInternship.domain.service.UniversityService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.persistence.mapper.PartnerMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PartnerMapper partnerMapper;
    private final MessageSource messageSource;
    private final UniversityService universityService;
    private final UniversityMapper universityMapper;
    private final InternshipProgrammeRepository internshipProgrammeRepository;
    private final InternshipProgrammeMapper internshipProgrammeMapper;
    private final FileService fileService;

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public List<PartnerDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PaginationDTO findAllByUniversityId(Long universityId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByUniversityId'");
    }

    @Override
    public PartnerDTO create(PartnerCreationDTO partnerCreationDTO, MultipartFile fileAvatar) {
        if (partnerCreationDTO.getPartnerOtherInfoDTO().getUniversityDTO().getId() == null) {
            partnerCreationDTO.getPartnerOtherInfoDTO()
                    .setUniversityDTO(universityService.create(partnerCreationDTO.getPartnerOtherInfoDTO().getUniversityDTO(), null));
        } else {
            partnerCreationDTO.getPartnerOtherInfoDTO()
                    .setUniversityDTO(universityService.findById(partnerCreationDTO.getPartnerOtherInfoDTO().getUniversityDTO().getId())
                            .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                    Collections.singletonMap(
                                            "University not found with ID",
                                            partnerCreationDTO.getPartnerOtherInfoDTO().getUniversityDTO().getId())))
                    );
        }

        UserDTO createdUserDTO = userService.create(partnerCreationDTO.getUserCreationDTO(), fileAvatar, ERole.Partner);

        // check confirm password
        if (!partnerCreationDTO.getUserCreationDTO().getPassword().equals(partnerCreationDTO.getUserCreationDTO().getConfirmPassword())) {
            throw new ValidationExceptionV2(
                    Collections.singletonMap(
                            "Confirm password: ",
                            messageSource.getMessage("error.passwordMatch", null, Locale.getDefault()))
            );
        }

        Partner partner = partnerMapper.toEntity(partnerCreationDTO);
        partner.getUser().setId(createdUserDTO.getId());

        PartnerDTO partnerDTO = partnerMapper.toDTO(partnerRepository.save(partner));
        partnerDTO.setUserDTO(createdUserDTO);
        return partnerDTO;
    }

    @Override
    public PartnerDTO updateUser(PartnerProfileDTO partnerProfileDTO, MultipartFile fileAvatar) {
        Long currentUserId = userService.getCurrentUserId();
        if (currentUserId == null) {
            throw new AccessDeniedExceptionV2();
        }

        // Lấy đối tượng Partner dựa trên User
        Partner oldPartner = partnerRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Partner not found for the authenticated user", currentUserId)));

        UserDTO updateUserDTO = userService.update(currentUserId, partnerProfileDTO.getUserProfileDTO(), fileAvatar);

        Partner updatePartner = partnerMapper.partnerUpdateToEntity(partnerProfileDTO);
        updatePartner.setId(oldPartner.getId());
        updatePartner.getUser().setId(updateUserDTO.getId());

        universityService.findById(oldPartner.getUniversity().getId())
                .ifPresent(universityDTO -> {
                    University university = universityMapper.toEntity(universityDTO);
                    updatePartner.setUniversity(university);
                });

        return partnerMapper.toDTO(partnerRepository.save(updatePartner));
    }

    public PaginationDTO filterInternship(InternshipProgrammeFilterDTO internshipProgrammeFilterDTO, int no, int limit) {
        Page<InternshipProgramme> page = internshipProgrammeRepository.searchInternshipProgrammes(
                internshipProgrammeFilterDTO.getTitle(),
                internshipProgrammeFilterDTO.getLocation(),
                internshipProgrammeFilterDTO.getStartDate(),
                internshipProgrammeFilterDTO.getEndDate(),
                internshipProgrammeFilterDTO.getStatusId(),
                PageRequest.of(no, limit)
        );

        List<InternshipProgrammeDTOShow> internshipProgrammeDTOShows = page.getContent().stream()
                .map(internshipProgrammeMapper::toDTOShow)
                .toList();

        return new PaginationDTO(
                internshipProgrammeDTOShows,
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Override
    public List<UniversityPartnerDTOShow> findByUniversityId(Long universityId) {
        List<Partner> partners = partnerRepository.findByUniversityId(universityId);
        return partners.stream()
                .map(partnerMapper::toUniversityPartnerDTOShow)
                .collect(Collectors.toList());
    }

    @Override
    public PartnerDTO findByUserId(Long userId) {
        return partnerMapper.toDTO(
                partnerRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("User not found with id:", userId))));
    }

    @Override
    public PartnerDTO findById(Long id) {
        return partnerRepository.findById(id)
                .map(partnerMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Partner not found with id:", id)));
    }

    @Override
    public PartnerDTO updateUser(
            PartnerCreationDTO partnerCreationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public PartnerCreationDTO readJson(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readJson'");
    }

}
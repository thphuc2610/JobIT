package com.r2s.findInternship.infracstructure.persistence.jpa.candidate;

import java.util.Collections;
import java.util.Map;

import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.application.dto.candidate.*;
import com.r2s.findInternship.domain.entity.University;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.UniversityRepository;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.UniversityMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.repository.CandidateRepository;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.CandidateMajorService;
import com.r2s.findInternship.domain.service.CandidatePositionService;
import com.r2s.findInternship.domain.service.CandidateScheduleService;
import com.r2s.findInternship.domain.service.CandidateService;
import com.r2s.findInternship.domain.service.FileService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.CandidateMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final UserService userService;
    private final CandidateMapper candidateMapper;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final CandidatePositionService candidatePositionService;
    private final CandidateMajorService candidateMajorService;
    private final CandidateScheduleService candidateScheduleService;
    private final HttpServletRequest request;

    @Override
    public boolean isCurrentAuthor(Long id) {
        Long candidateUserId = candidateRepository.findById(id).map(c -> c.getUser().getId()).orElse(null);
        Long currentUserId = userService.getCurrentUserId();

        return (candidateUserId != null) && (currentUserId != null) && candidateUserId.equals(currentUserId);
    }

    @Override
    public CandidateDTO create(CandidateCreationDTO candidateCreationDTO,
                               MultipartFile fileAvatar) {
        // Kiểm tra candidateCreationDTO và userCreationDTO
        if (candidateCreationDTO == null || candidateCreationDTO.getUserCreationDTO() == null) {
            throw new ValidationExceptionV2(Map.of());
        }

        UserDTO createdUserDTO = userService.create(
                candidateCreationDTO.getUserCreationDTO(), fileAvatar, ERole.Candidate);

        Candidate candidate = candidateMapper.toEntity(candidateCreationDTO);
        candidate.getUser().setId(createdUserDTO.getId());

        CandidateDTO createdCandidateDTO = candidateMapper.toDTO(candidateRepository.save(candidate));
        createdCandidateDTO.setUserDTO(createdUserDTO);

        return createdCandidateDTO;
    }

    @Override
    public CandidateDTO updatePersonalInfo(CandidateProfileDTO candidateProfileDTO, MultipartFile fileAvatar) {
        Long id = userService.getCurrentUserId();

        Candidate oldCandidate = candidateRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Candidate not found with id", id)));

        UserDTO updatedUserDTO = userService.update(
                oldCandidate.getUser().getId(),
                candidateProfileDTO.getUserProfileDTO(),
                fileAvatar
        );

        // Cập nhật trường đại học nếu có
        UniversityDTO universityDTO = candidateProfileDTO.getCandidateOtherInfoDTO().getUniversityDTO();
        if (universityDTO != null && universityDTO.getId() != null) {
            University existingUniversity = universityRepository.findById(universityDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("UniversityID", universityDTO.getId())));
            oldCandidate.setUniversity(existingUniversity);
        }

        CandidateDTO updatedCandidateDTO = candidateMapper.toDTO(candidateRepository.save(oldCandidate));
        updatedCandidateDTO.setUserDTO(updatedUserDTO);
        return updatedCandidateDTO;
    }

    @Override
    public CandidateDTO updateJobInfo(CandidateProfileDTO candidateProfileDTO, MultipartFile fileCV) {
        Long id = userService.getCurrentUserId();

        Candidate oldCandidate = candidateRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Candidate not found with id: ", id)));

        // Cập nhật thông tin công việc
        CandidateOtherInfoDTO info = candidateProfileDTO.getCandidateOtherInfoDTO();

        if (info.getDesiredJob() != null) {
            oldCandidate.setDesiredJob(info.getDesiredJob());
        }

        if (info.getDesiredWorkingProvince() != null) {
            oldCandidate.setDesiredWorkingProvince(info.getDesiredWorkingProvince());
        }

        if (info.getReferenceLetter() != null) {
            oldCandidate.setReferenceLetter(info.getReferenceLetter());
        }

        // Cập nhật CV nếu có file
        if (fileCV != null) {
            if (oldCandidate.getCV() != null) {
                fileService.deleteFile(oldCandidate.getCV());
            }
            oldCandidate.setCV(fileService.uploadFile(fileCV));
        }

        Candidate saved = candidateRepository.save(oldCandidate);

        // Cập nhật các bảng liên quan: position, major, schedule
        candidatePositionService.update(saved.getId(), info.getPositionDTOs());
        candidateMajorService.update(saved.getId(), info.getMajorDTOs());
        candidateScheduleService.update(saved.getId(), info.getScheduleDTOs());

        CandidateDTO updatedCandidateDTO = candidateMapper.toDTO(saved);
        updatedCandidateDTO.getCandidateOtherInfoDTO().setPositionDTOs(info.getPositionDTOs());
        updatedCandidateDTO.getCandidateOtherInfoDTO().setMajorDTOs(info.getMajorDTOs());
        updatedCandidateDTO.getCandidateOtherInfoDTO().setScheduleDTOs(info.getScheduleDTOs());

        return updatedCandidateDTO;
    }

    @Override
    public CandidateDTO findByUserId(long userId) {
        return candidateMapper.toDTO(
                candidateRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(

                                Collections.singletonMap("userId", userId))));
    }

    @Override
    public CandidateDTO findById(long id) {
        return candidateMapper.toDTO(
                candidateRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                Collections.singletonMap("CandidateId", id))));
    }

    @Override
    public MessageResponse updateSearchable() {
        Long id = userService.getCurrentUserId();

        Candidate candidate = candidateRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Candidate not found with id: ", id)));

        candidate.setSearchable(!candidate.isSearchable());
        candidateRepository.save(candidate);

        return new MessageResponse(HttpServletResponse.SC_OK, String.valueOf(candidate.isSearchable()), request.getServletPath());
    }

    @Override
    public MessageResponse updateMailReceive() {
        Long id = userService.getCurrentUserId();

        Candidate candidate = candidateRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Candidate not found with id:", id)));

        User user = candidate.getUser();
        user.setMailReceive(!user.isMailReceive());
        userRepository.save(user);

        return new MessageResponse(HttpServletResponse.SC_OK, String.valueOf(user.isMailReceive()), request.getServletPath());
    }


    @Override
    public PaginationDTO filterByHR(CandidateFilterByHRDTO candidateFilterByHRDTO, int no, int limit) {
        if (candidateFilterByHRDTO.getDesiredJob() != null
                && candidateFilterByHRDTO.getDesiredJob().isBlank()) {
            candidateFilterByHRDTO.setDesiredJob(null);
        }

        if (candidateFilterByHRDTO.getDesiredWorkingProvince() != null
                && candidateFilterByHRDTO.getDesiredWorkingProvince().isBlank()) {
            candidateFilterByHRDTO.setDesiredWorkingProvince(null);
        }

        if (candidateFilterByHRDTO.getScheduleIds() != null
                && candidateFilterByHRDTO.getScheduleIds().isEmpty()) {
            candidateFilterByHRDTO.setScheduleIds(null);
        }

        if (candidateFilterByHRDTO.getPositionIds() != null
                && candidateFilterByHRDTO.getPositionIds().isEmpty()) {
            candidateFilterByHRDTO.setPositionIds(null);
        }

        if (candidateFilterByHRDTO.getMajorIds() != null
                && candidateFilterByHRDTO.getMajorIds().isEmpty()) {
            candidateFilterByHRDTO.setMajorIds(null);
        }

        Page<CandidateDTO> page = candidateRepository.filterByHr(
                        candidateFilterByHRDTO.getDesiredJob(),
                        candidateFilterByHRDTO.getDesiredWorkingProvince(),
                        candidateFilterByHRDTO.getScheduleIds(),
                        candidateFilterByHRDTO.getPositionIds(),
                        candidateFilterByHRDTO.getMajorIds(),
                        PageRequest.of(no, limit))
                .map(c -> candidateMapper.toDTO(c));

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }
}
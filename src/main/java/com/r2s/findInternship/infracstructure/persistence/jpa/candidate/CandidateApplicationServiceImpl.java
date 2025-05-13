package com.r2s.findInternship.infracstructure.persistence.jpa.candidate;

import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.repository.JobRepository;
import com.r2s.findInternship.domain.service.*;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ConflictExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2s.findInternship.application.dto.candidate.CandidateApplicationDTO;
import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.application.dto.show.ApplicationDTONotShowJob;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidateApplication;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.CandidateApplicationRepository;
import com.r2s.findInternship.domain.repository.CandidateRepository;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.persistence.mapper.CandidateApplicationMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class CandidateApplicationServiceImpl implements CandidateApplicationService {
    private final FileService fileService;
    private final CandidateApplicationRepository candidateApplicationRepository;
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;
    private final CandidateApplicationMapper candidateApplicationMapper;
    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final UserService userService;
    private final HRService hrService;
    private final JobService jobService;
    private final JobRepository jobRepository;
    private final HttpServletRequest request;

    public final static Logger LOGGER = LoggerFactory.getLogger("info");
    public final static int zeroValue = 0;
    @Override
    public PaginationDTO findAllByCandidateId(int no, int limit) {
        UserDTO userDTO = userService.getCurrentLoginUser();

        Candidate candidate = candidateRepository.findByUserId(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("CandidateId with UserId", userDTO.getId())));

        Page<CandidateApplicationDTO> applicationPages = candidateApplicationRepository
                .findAllByCandidateIdOrderByCreatedDateDesc(candidate.getId(), PageRequest.of(no, limit))
                .map(this.candidateApplicationMapper::toDTO);

        return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
                applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
                applicationPages.getNumber());
    }

    // Lấy job theo candidateId
    @Override
    public CandidateApplicationDTO findByCandidateApplicationId(long candidateApplicationId) {
        UserDTO userDTO = userService.getCurrentLoginUser();

        CandidateApplication application = candidateApplicationRepository.findById(candidateApplicationId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("CandidateApplicationId not found", candidateApplicationId)));

        if (application.getCandidate().getUser().getId() != userDTO.getId()) {
            throw new AccessDeniedExceptionV2();
        }

        return candidateApplicationMapper.toDTO(application);
    }

    // Lấy tất cả theo jobId
    @Override
    public PaginationDTO findAllByJobId(long jobId, int no, int limit) {
        if (!jobRepository.existsById(jobId)) {
            throw new ResourceNotFoundExceptionV2(Collections.singletonMap("Not found with job id: ", jobId));
        }

        Long hrId = hrService.findByUserId(userService.getCurrentUserId()).getId();

        boolean isJobOwnedByHr = jobRepository.existsByIdAndHrId(jobId, hrId);
        if (!isJobOwnedByHr) throw new AccessDeniedExceptionV2();

        Page<ApplicationDTONotShowJob> applicationPages = candidateApplicationRepository
                .findAllByJobId(jobId, PageRequest.of(no, limit))
                .map(candidateApplicationMapper::toDTONotShowJob);

        if (applicationPages.isEmpty()) {
            throw new ResourceNotFoundExceptionV2(
                    Collections.singletonMap("Not found candidate application this job ", ""));
        }

        return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
                applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
                applicationPages.getNumber());
    }

    @Override
    public boolean checkCandidateApplication(int idJob) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email).orElseThrow();
        CandidateDTO candidateDTO = candidateService.findByUserId(user.getId());

        if (this.existsByJobIdAndCandidateId(idJob,
                candidateDTO.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public CandidateApplicationDTO create(CandidateApplicationDTO candidateApplicationDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("User not found", email)));
        CandidateDTO candidateDTO = candidateService.findByUserId(user.getId());

        // check apply
        if (this.existsByJobIdAndCandidateId(candidateApplicationDTO.getJobDTO().getId(), candidateDTO.getId())) {
            throw new ConflictExceptionV2(Collections.singletonMap("You have already applied for this job.", candidateApplicationDTO.getJobDTO().getId()));
        }

        // get information this job and check job apply
        JobDTO jobDTO = jobService.findById(candidateApplicationDTO.getJobDTO().getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap(
                        "Not found with id:", candidateApplicationDTO.getJobDTO().getId())));

        if (!jobService.isAppliable(jobDTO)) {
            throw new ResponseStatusException(HttpStatus.GONE, "The job with ID " + jobDTO.getId() + " is no longer available for applications.");
        }
        candidateApplicationDTO.setJobDTO(jobDTO);

        // Set value candidate apply
        candidateApplicationDTO.setCandidateDTO(candidateDTO);
        candidateApplicationDTO.setEmail(candidateDTO.getUserDTO().getEmail());
        candidateApplicationDTO
                .setFullName(candidateDTO.getUserDTO().getLastName() + " " + candidateDTO.getUserDTO().getFirstName());
        candidateApplicationDTO.setPhone(candidateDTO.getUserDTO().getPhone());

        // Nếu ứng viên không gửi CV khi ứng tuyển, sử dụng CV đã có sẵn trong hồ sơ
        if (candidateApplicationDTO.getCV() == null || candidateApplicationDTO.getCV().isEmpty()) {
            candidateApplicationDTO.setCV(candidateDTO.getCandidateOtherInfoDTO().getCV());
        }

        // map value to enetity to save
        CandidateApplication candidateApplication = candidateApplicationMapper.toEntity(candidateApplicationDTO);
        candidateApplication.setId(zeroValue);
        candidateApplication = candidateApplicationRepository.save(candidateApplication);

        // Map value to dto to return
        CandidateApplicationDTO applicationDTO = candidateApplicationMapper.toDTO(candidateApplication);
        applicationDTO.setJobDTO(jobDTO);

        return applicationDTO;
    }

    @Override
    public CandidateApplicationDTO readJson(String value, MultipartFile fileCV) {

        CandidateApplicationDTO candidateApplicationDTO = new CandidateApplicationDTO();

        try {
            ObjectMapper ob = new ObjectMapper();
            candidateApplicationDTO = ob.readValue(value, CandidateApplicationDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(this.messageSource.getMessage("error.readJson", null, null));
        }

        // Check file CV
        if (fileCV == null || fileCV.isEmpty()) {
            throw new InternalServerErrorException("CV is required to apply for this job.");
        }

        // Upload CV và save file to DTO
        String fileCVName = fileService.uploadFile(fileCV);
        candidateApplicationDTO.setCV(fileCVName);

        return candidateApplicationDTO;
    }

    @Override
    public boolean existsByJobIdAndCandidateId(long jobId, long candidateId) {
        return candidateApplicationRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }


    @Override
    public MessageResponse deleteById(long id) {
        UserDTO userDTO = userService.getCurrentLoginUser();

        CandidateApplication application = candidateApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("CandidateApplicationId not found", id)));

        if (application.getCandidate().getUser().getId() != userDTO.getId()) {
            throw new AccessDeniedExceptionV2();
        }

        candidateApplicationRepository.deleteById(id);

        return new MessageResponse(HttpServletResponse.SC_OK, "Deleted successful", request.getServletPath());
    }
}
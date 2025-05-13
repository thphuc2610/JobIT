package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.JobCareDTO;
import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.JobCare;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.repository.CandidateRepository;
import com.r2s.findInternship.domain.repository.JobCareRepository;
import com.r2s.findInternship.domain.repository.JobRepository;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.CandidateService;
import com.r2s.findInternship.domain.service.JobCareService;
import com.r2s.findInternship.domain.service.JobService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ConflictExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.JobCareMapper;

import com.r2s.findInternship.infracstructure.persistence.mapper.JobMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobCareServiceImpl implements JobCareService {
    private final CandidateRepository candidateRepository;
    private final CandidateService candidateService;
    private final JobRepository jobRepository;
    private final JobService jobService;
    private final JobCareMapper jobCareMapper;
    private final JobCareRepository jobCareRepository;
    private final UserService userService;
    private final JobMapper jobMapper;
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;

    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public PaginationDTO findAllByCandidateId(int no, int limit) {
        Long candidateId = candidateService.findByUserId(userService.getCurrentUserId()).getId();

        Page<Object> page = this.jobCareRepository
                .findAllByCandidateId(candidateId, PageRequest.of(no, limit))
                .map(jobCareMapper::toDTO);

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
    public List<JobDTO> findJobSaveOfCandidateID() {
        Candidate candidate = candidateRepository.findByUserId(userService.getCurrentUserId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Candidate not found", userService.getCurrentUserId())
                ));

        List<Integer> listJobId = this.jobCareRepository.findJobSave(candidate.getId());
        if (listJobId.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> jobIds = listJobId.stream()
                .map(Integer::longValue)
                .collect(Collectors.toList());

        return jobRepository.findAllById(jobIds).stream()
                .map(job -> jobMapper.toDTOShow(job))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobCareDTO> findAll() {
        return this.jobCareRepository.findAll().stream().map(item -> this.jobCareMapper.toDTO(item))
                .collect(Collectors.toList());

    }

    @Override
    public JobCareDTO findById(int id) {
        return this.jobCareMapper.toDTO(this.jobCareRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Job care", "id",
                        String.valueOf(id))));

    }

    @Override
    public JobCareDTO findByCandidateIdAndJobId(int candidateId, int jobId) {
        return this.jobCareMapper.toDTO(this.jobCareRepository.findByCandidateIdAndJobId(candidateId, jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job care", "(candidateId, jobId)",
                        "(" + candidateId + ", " + jobId + ")")));

    }

    // Save job by candidate
    @Override
    public MessageResponse create(long idJob) {
        // Get information
        JobDTO jobDTO = jobService.findById(idJob)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Job not found", idJob)));

        CandidateDTO candidateDTO = candidateService.findByUserId(userService.getCurrentUserId());

        // Check status job
        if (jobDTO.getStatusDTO().getId() != Estatus.activeStatus) {
            throw new ValidationExceptionV2(Collections.singletonMap("Job not active", Estatus.activeStatus));
        }

        // Check id job care
        if (jobCareRepository.existsByCandidateIdAndJobId(candidateDTO.getId(), jobDTO.getId())) {
            throw new ConflictExceptionV2(Map.of(
                    "message", "This job has already been saved by the candidate.",
                    "candidateId", candidateDTO.getId(),
                    "jobId", jobDTO.getId()));
        }

        // Save
        JobCareDTO jobCareDTO = new JobCareDTO();
        jobCareDTO.setJobDTO(jobDTO);
        jobCareDTO.setCandidateDTO(candidateDTO);

        JobCare care = jobCareMapper.toEntity(jobCareDTO);
        jobCareRepository.save(care);

        return new MessageResponse(HttpServletResponse.SC_OK, "Job saved successfully", httpServletRequest.getServletPath());
    }

    // Delete job by candidate
    @Override
    public MessageResponse deleteByJobIdAndCandidateId(long idJob) {
        // Get id candidate
        CandidateDTO candidateDTO = candidateService.findByUserId(userService.getCurrentUserId());
        if (candidateDTO == null) {
            throw new ResourceNotFoundExceptionV2(Collections.singletonMap("Candidate not found with id: ", userService.getCurrentUserId()));
        }

        // Get id job want to delete
        JobCare jobCare = jobCareRepository.findByJobIdAndCandidateId(idJob, candidateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Job not found with id: ", idJob)));

        // Check candidate id belong to id job care
        if (candidateDTO.getId() != jobCare.getCandidate().getId()) {
            throw new AccessDeniedExceptionV2();
        }

        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Map.of("message", "JOB NOT FOUND", "id", idJob)));

        jobCareRepository.delete(jobCare);
        return new MessageResponse(HttpServletResponse.SC_OK, "Job deleted successfully", httpServletRequest.getServletPath());
    }

    @Override
    public boolean checkCandidateApplication(int idJob) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email).orElseThrow();
        CandidateDTO candidateDTO = candidateService.findByUserId(user.getId());

        return this.existsByJobIdAndCandidateId(idJob, candidateDTO.getId());
    }

    @Override
    public boolean existsByJobIdAndCandidateId(long jobId, long candidateId) {
        return jobCareRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }

    @Override
    public PaginationDTO findAllByJobId(int jobId, int no, int limit) {
        throw new UnsupportedOperationException("Unimplemented method 'findAllByJobId'");
    }
}
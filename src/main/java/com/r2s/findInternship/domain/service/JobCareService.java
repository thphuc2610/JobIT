package com.r2s.findInternship.domain.service;

import java.util.List;
import java.util.Optional;

import com.r2s.findInternship.application.dto.JobCareDTO;
import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.JobCare;

public interface JobCareService {
    PaginationDTO findAllByCandidateId(int no, int limit);

    List<JobDTO> findJobSaveOfCandidateID();

    PaginationDTO findAllByJobId(int jobId, int no, int limit);

    List<JobCareDTO> findAll();

    JobCareDTO findById(int id);

    JobCareDTO findByCandidateIdAndJobId(int candidateId, int jobId);

    MessageResponse create(long idJob);

    MessageResponse deleteByJobIdAndCandidateId(long idJob);

    boolean checkCandidateApplication(int idJob);

    boolean existsByJobIdAndCandidateId(long jobId, long candidateId);
}
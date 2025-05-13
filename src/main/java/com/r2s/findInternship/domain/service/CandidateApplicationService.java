package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.domain.common.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.candidate.CandidateApplicationDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;

public interface CandidateApplicationService {
    PaginationDTO findAllByCandidateId(int no, int limit);

    CandidateApplicationDTO findByCandidateApplicationId(long id);

    PaginationDTO findAllByJobId(long jobId, int no, int limit);

    CandidateApplicationDTO readJson(String value, MultipartFile fileCV);

    boolean existsByJobIdAndCandidateId(long jobId, long candidateId);

    boolean checkCandidateApplication(int idJob);

    CandidateApplicationDTO create(CandidateApplicationDTO candidateApplicationDTO);

    MessageResponse deleteById(long id);
}
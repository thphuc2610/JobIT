package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.hr.HrSaveCandidateDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.HRSaveCandidate;

import java.util.Optional;

public interface HRSaveCandidateService {
    PaginationDTO findAllByHrId(int no, int limit);

    HrSaveCandidateDTO findById(Long id);

    MessageResponse saveCandidate(Long candidateId);

    MessageResponse deleteByCandidateIdAndHrId(Long candidateId);

    boolean existsByCandidateIdAndHrId(Long candidateId, Long hrId);
}
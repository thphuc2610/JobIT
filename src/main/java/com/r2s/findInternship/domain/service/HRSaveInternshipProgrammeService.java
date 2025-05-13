package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.common.MessageResponse;

public interface HRSaveInternshipProgrammeService {
    PaginationDTO findAllByHrId(int no, int limit);

    MessageResponse saveInternshipProgramme(Long internshipProgrammeId);

    MessageResponse deleteByInternshipProgrammeIdAndHrId(Long internshipProgrammeId);

    boolean existsByInternshipProgrammeIdAndHrId(Long internshipProgrammeId, Long hrId);
}
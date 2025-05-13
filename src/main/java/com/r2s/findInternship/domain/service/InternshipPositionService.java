package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.InternshipProgramme;

public interface InternshipPositionService {
    boolean created(InternshipProgramme internshipProgramme, PositionDTO positionDTO);
}
package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.PositionDTO;

public interface CandidatePositionService {
    boolean update(long candidateId, List<PositionDTO> positionDTOs);
}
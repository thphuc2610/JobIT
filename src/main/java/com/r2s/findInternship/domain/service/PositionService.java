package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.common.MessageResponse;

import java.util.List;

public interface PositionService {
    PositionDTO findById(Integer id);

    List<PositionDTO> findAll();

    MessageResponse create(PositionDTO positionDTO);

    MessageResponse deleteById(Integer id);
}
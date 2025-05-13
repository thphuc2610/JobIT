package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.common.MessageResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleDTO findById(Integer id);

    List<ScheduleDTO> findAll();

    MessageResponse create(ScheduleDTO scheduleDTO);

    MessageResponse deleteById(Integer id);
}
package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.ScheduleDTO;

public interface CandidateScheduleService {
    boolean update(long candidateId, List<ScheduleDTO> scheduleDTOs);
}
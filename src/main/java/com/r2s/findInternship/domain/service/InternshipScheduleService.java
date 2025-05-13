package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.InternshipProgramme;

public interface InternshipScheduleService {
    boolean created(InternshipProgramme internshipProgramme, ScheduleDTO scheduleDTO);
}
package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobSchedule;

public interface JobScheduleService {
    JobSchedule createdJobSchedule(Job job, ScheduleDTO scheduleDTO);
}

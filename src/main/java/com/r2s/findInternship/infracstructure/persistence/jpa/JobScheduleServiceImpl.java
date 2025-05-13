package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobSchedule;
import com.r2s.findInternship.domain.repository.JobScheduleRepository;
import com.r2s.findInternship.domain.repository.ScheduleRepository;
import com.r2s.findInternship.domain.service.JobScheduleService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class JobScheduleServiceImpl implements JobScheduleService {
    private final JobScheduleRepository jobScheduleRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public JobSchedule createdJobSchedule(Job job, ScheduleDTO scheduleDTO) {
        if (scheduleDTO != null) {
            JobSchedule jobSchedule = new JobSchedule();
            jobSchedule.setJob(job);
            jobSchedule.setSchedule(scheduleRepository.findById(scheduleDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Schedule not found with id: ", scheduleDTO.getId()))));
            return jobScheduleRepository.save(jobSchedule);
        }
        return null;
    }
}
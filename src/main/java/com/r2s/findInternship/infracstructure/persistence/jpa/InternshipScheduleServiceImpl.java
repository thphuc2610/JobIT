package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.InternshipSchedule;
import com.r2s.findInternship.domain.repository.InternshipScheduleRepository;
import com.r2s.findInternship.domain.repository.ScheduleRepository;
import com.r2s.findInternship.domain.service.InternshipScheduleService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InternshipScheduleServiceImpl implements InternshipScheduleService {
    private final InternshipScheduleRepository internshipScheduleRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public boolean created(InternshipProgramme internshipProgramme, ScheduleDTO scheduleDTO) {
        if (scheduleDTO != null) {
            InternshipSchedule internshipSchedule = new InternshipSchedule();
            internshipSchedule.setInternshipProgramme(internshipProgramme);
            internshipSchedule.setSchedule(scheduleRepository.findById(scheduleDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Major not found with id: ", scheduleDTO.getId()))));
            internshipScheduleRepository.save(internshipSchedule);
            return true;
        }
        return false;
    }
}
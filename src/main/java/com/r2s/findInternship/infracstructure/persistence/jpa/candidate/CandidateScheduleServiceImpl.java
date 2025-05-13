package com.r2s.findInternship.infracstructure.persistence.jpa.candidate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.r2s.findInternship.domain.repository.ScheduleRepository;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidateSchedule;
import com.r2s.findInternship.domain.entity.Schedule;
import com.r2s.findInternship.domain.repository.CandidateScheduleRepository;
import com.r2s.findInternship.domain.service.CandidateScheduleService;

@Service
@RequiredArgsConstructor
public class CandidateScheduleServiceImpl implements CandidateScheduleService {

    private final CandidateScheduleRepository candidateScheduleRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public boolean update(long candidateId, List<ScheduleDTO> scheduleDTOs) {
        Queue<CandidateSchedule> oldCandidateSchedules = new LinkedList<>(
                candidateScheduleRepository.findAllByCandidate_Id(candidateId));

        if (scheduleDTOs == null || scheduleDTOs.isEmpty()) {
            candidateScheduleRepository.deleteAll(oldCandidateSchedules);
            return true;
        }

        for (ScheduleDTO newScheduleDTO : scheduleDTOs) {
            Schedule schedule = scheduleRepository.findById(newScheduleDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

            if (oldCandidateSchedules.isEmpty()) {
                CandidateSchedule newCandidateSchedule = new CandidateSchedule();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);

                newCandidateSchedule.setCandidate(candidate);
                newCandidateSchedule.setSchedule(schedule);
                candidateScheduleRepository.save(newCandidateSchedule);
            } else {
                CandidateSchedule candidateSchedule = oldCandidateSchedules.poll();
                candidateSchedule.setSchedule(schedule);
                candidateScheduleRepository.save(candidateSchedule);
            }
        }

        while (!oldCandidateSchedules.isEmpty()) {
            candidateScheduleRepository.delete(oldCandidateSchedules.poll());
        }

        return true;
    }
}
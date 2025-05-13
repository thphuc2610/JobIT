package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.util.Collections;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobPosition;
import com.r2s.findInternship.domain.repository.JobPositionRepository;
import com.r2s.findInternship.domain.repository.PositionRepository;
import com.r2s.findInternship.domain.service.JobPositionService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {
    private final JobPositionRepository jobPositionRepository;
    private final PositionRepository positionRepository;

    @Override
    public JobPosition createdJobPosition(Job job, PositionDTO positionDTO) {
        if (positionDTO != null) {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setJob(job);
            jobPosition.setPosition(positionRepository.findById(positionDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Position not found with id: ", positionDTO.getId()))));
            return jobPositionRepository.save(jobPosition);
        }
        return null;
    }
}
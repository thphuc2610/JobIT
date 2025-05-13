package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobMajor;
import com.r2s.findInternship.domain.repository.JobMajorRepository;
import com.r2s.findInternship.domain.repository.MajorRepository;
import com.r2s.findInternship.domain.service.JobMajorService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JobMajorServiceImpl implements JobMajorService {
    private final JobMajorRepository jobMajorRepository;
    private final MajorRepository majorRepository;

    @Override
    public JobMajor createdJobMajor(Job job, MajorDTO majorDTO) {
        if (majorDTO != null) {
            JobMajor jobMajor = new JobMajor();
            jobMajor.setJob(job);
            jobMajor.setMajor(majorRepository.findById(majorDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Major not found with id: ", majorDTO.getId()))));
            return jobMajorRepository.save(jobMajor);
        }
        return null;
    }
}
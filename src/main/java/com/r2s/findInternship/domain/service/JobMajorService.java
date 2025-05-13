package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobMajor;

public interface JobMajorService {
    JobMajor createdJobMajor(Job job, MajorDTO majorDTO);
}

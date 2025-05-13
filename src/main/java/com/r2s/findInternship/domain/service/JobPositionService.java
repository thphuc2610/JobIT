package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobPosition;

public interface JobPositionService {
	JobPosition createdJobPosition(Job job, PositionDTO positionDTO);
}
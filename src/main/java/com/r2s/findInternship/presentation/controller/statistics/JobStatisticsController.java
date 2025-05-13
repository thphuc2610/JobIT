package com.r2s.findInternship.presentation.controller.statistics;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.JobService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.JOB_STATISTIC)
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize(value = "hasRole('Admin')")
public class JobStatisticsController {
	@Autowired
	private JobService jobService;
}
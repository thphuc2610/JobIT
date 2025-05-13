package com.r2s.findInternship.presentation.controller.statistics;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.UniversityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.UNIVERSITY_STATISTIC)
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize(value = "hasRole('Admin')")
public class UniversityStatisticsController {
	@Autowired
	private UniversityService universityService;

	@GetMapping("/new")
	public ResponseEntity<?> getNewStatistics() {
		return ResponseEntity.ok(this.universityService.getNewStatistics());
	}

	@GetMapping("/count")
	public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
		return ResponseEntity.ok(this.universityService.countByCreatedDate(from, to));
	}

	@GetMapping("/status")
	public ResponseEntity<?> getStatusStatistics() {
		return ResponseEntity.ok(this.universityService.getStatusStatistics());
	}
}
package com.r2s.findInternship.presentation.controller.statistics;

import java.util.Date;

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
import com.r2s.findInternship.domain.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.USER_STATISTIC)
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize(value = "hasRole('Admin')")
public class UserStatisticsController {
	@Autowired
	private UserService userService;

	@GetMapping("/gender")
	public ResponseEntity<?> getGenderStatistics() {
		return ResponseEntity.ok(this.userService.getGenderStatistics());
	}

	@GetMapping("/count")
	public ResponseEntity<?> countByCreatedDate(@RequestParam Date from, @RequestParam Date to) {
		return ResponseEntity.ok(this.userService.countByCreatedDate(from, to));
	}

	@GetMapping("/role")
	public ResponseEntity<?> getRoleStatistics() {
		return ResponseEntity.ok(this.userService.getRoleStatistics());
	}

	@GetMapping("/status")
	public ResponseEntity<?> getStatusStatistics() {
		return ResponseEntity.ok(this.userService.getStatusStatistics());
	}

	@GetMapping("/new")
	public ResponseEntity<?> getNewStatistics() {
		return ResponseEntity.ok(this.userService.getNewStatistics());
	}
}
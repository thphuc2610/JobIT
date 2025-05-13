package com.r2s.findInternship.presentation.controller.hr;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.HRApplicationService;
import com.r2s.findInternship.domain.service.JobService;

import java.time.LocalDate;
import java.time.Month;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.HR_APPLICATION)
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('Role_HR')")
public class HRApplicationController {
    private final HRApplicationService hrApplicationService;
    private final JobService jobService;

    @GetMapping("/Recruitment-News")
    public ResponseEntity<?> getRecruitmentNews() {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        int monthValue = currentMonth.getValue();
        return ResponseEntity.ok(this.jobService.recruitmentNews(monthValue));
    }
}
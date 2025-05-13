package com.r2s.findInternship.presentation.controller.partner;

import com.r2s.findInternship.domain.constant.ApiURL;

import com.r2s.findInternship.domain.service.PartnerJobCareService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.PARTNER_JOB_CARE)
public class PartnerJobCareController {
    private final PartnerJobCareService partnerJobCareService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(partnerJobCareService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerJobCareService.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @GetMapping("/job-save")
    public ResponseEntity<?> findAllJobSave() {
        return ResponseEntity.ok(this.partnerJobCareService.findJobSaveOfPartnerId());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @PostMapping
    public ResponseEntity<?> create(@RequestParam("jobId") Long jobId) {
        return ResponseEntity.ok(this.partnerJobCareService.create(jobId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @DeleteMapping
    public ResponseEntity<?> deleteByJobIdAndPartnerId(@RequestParam("jobId") Long jobId) {
        return ResponseEntity.ok(this.partnerJobCareService.deleteByJobIdAndPartnerId(jobId));
    }
}
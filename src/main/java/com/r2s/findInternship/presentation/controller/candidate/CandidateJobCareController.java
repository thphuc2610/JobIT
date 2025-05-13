package com.r2s.findInternship.presentation.controller.candidate;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.JobCareService;

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
@RequestMapping(ApiURL.CANDIDATE_JOB_CARE)
public class CandidateJobCareController {
    private final JobCareService jobCareService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.jobCareService.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping("/job-save")
    public ResponseEntity<?> findAllJobSave() {
        return ResponseEntity.ok(this.jobCareService.findJobSaveOfCandidateID());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping
    public ResponseEntity<?> findAllByCandidateId(
            @RequestParam(defaultValue = PageDefault.NO) int no,
            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.jobCareService.findAllByCandidateId(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @PostMapping
    public ResponseEntity<?> create(@RequestParam("idJob") long idJob) {
        return ResponseEntity.ok(this.jobCareService.create(idJob));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @DeleteMapping
    public ResponseEntity<?> deleteByJobIdAndCandidateId(@RequestParam("idJob") long idJob) {
        return ResponseEntity.ok(this.jobCareService.deleteByJobIdAndCandidateId(idJob));
    }

    @GetMapping("/check")
    public boolean checkCandidateJobCare(@RequestParam("idJob") int idJob) {
        return this.jobCareService.checkCandidateApplication(idJob);
    }
}
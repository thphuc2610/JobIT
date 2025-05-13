package com.r2s.findInternship.presentation.controller.hr;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.HRSaveCandidateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.HR_SAVE_CANDIDATE)
public class HRSaveCandidateController {
    private final HRSaveCandidateService hrSaveCandidateService;

    // Get
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping
    public ResponseEntity<?> findAllByHrId(@RequestParam(defaultValue = PageDefault.NO) int no,
                                           @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(hrSaveCandidateService.findAllByHrId(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hrSaveCandidateService.findById(id));
    }

    // Post
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> saveCandidate(@PathVariable Long id) {
        return ResponseEntity.ok(hrSaveCandidateService.saveCandidate(id));
    }

    // Delete
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteSavedCandidate(@PathVariable Long id) {
        return ResponseEntity.ok(hrSaveCandidateService.deleteByCandidateIdAndHrId(id));
    }
}
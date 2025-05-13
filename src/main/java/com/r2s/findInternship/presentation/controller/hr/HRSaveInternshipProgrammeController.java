package com.r2s.findInternship.presentation.controller.hr;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.HRSaveInternshipProgrammeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.HR_SAVE_INTERNSHIP_PROGRAMME)
public class HRSaveInternshipProgrammeController {
    private final HRSaveInternshipProgrammeService hrSaveInternshipProgrammeService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping("/{internshipProgrammeId}")
    public ResponseEntity<?> saveInternshipProgramme(@PathVariable Long internshipProgrammeId) {
        return ResponseEntity.ok(hrSaveInternshipProgrammeService.saveInternshipProgramme(internshipProgrammeId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @DeleteMapping("/{internshipProgrammeId}")
    public ResponseEntity<?> deleteInternshipProgramme(@PathVariable Long internshipProgrammeId) {
        return ResponseEntity.ok(hrSaveInternshipProgrammeService.deleteByInternshipProgrammeIdAndHrId(internshipProgrammeId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping
    public ResponseEntity<?> getSavedInternshipProgrammes(
            @RequestParam int no,
            @RequestParam int limit) {
        return ResponseEntity.ok(hrSaveInternshipProgrammeService.findAllByHrId(no, limit));
    }
}

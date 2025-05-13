package com.r2s.findInternship.presentation.controller.candidate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.candidate.CandidateApplicationDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.CandidateApplicationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE_APPLICATION)
public class CandidateApplicationController {
    private final CandidateApplicationService candidateApplicationService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping
    public ResponseEntity<?> findAllByCandidateId(@RequestParam(defaultValue = PageDefault.NO) int no,
                                                  @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.candidateApplicationService.findAllByCandidateId(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateApplicationById(@PathVariable long id) {
        return ResponseEntity.ok(candidateApplicationService.findByCandidateApplicationId(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/job/{id}")
    public ResponseEntity<?> findAllByJobId(@PathVariable int id, @RequestParam(defaultValue = PageDefault.NO) int no,
                                            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.candidateApplicationService.findAllByJobId(id, no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> create(@ModelAttribute("candidateApplication") String candidateApplication,
                                    @ModelAttribute(name = "fileCV") MultipartFile fileCV) {

        // Read JSON and upload CV
        CandidateApplicationDTO candidateApplicationDTO = candidateApplicationService.readJson(candidateApplication,
                fileCV);
        return new ResponseEntity<>(this.candidateApplicationService.create(candidateApplicationDTO), HttpStatus.CREATED);
    }

    @GetMapping("/check")
    public boolean checkCandidateApplication(@RequestParam("idJob") int idJob) {
        return this.candidateApplicationService.checkCandidateApplication(idJob);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(candidateApplicationService.deleteById(id));
    }
}
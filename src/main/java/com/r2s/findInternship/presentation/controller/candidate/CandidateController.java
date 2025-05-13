package com.r2s.findInternship.presentation.controller.candidate;

import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.r2s.findInternship.application.dto.candidate.CandidateFilterByHRDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.CandidateService;
import com.r2s.findInternship.domain.service.JsonReaderService;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE)
@Validated
public class CandidateController {
    private final CandidateService candidateService;
    private final JsonReaderService<Object> jsonReaderService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable long id) {
        return ResponseEntity.ok(candidateService.findByUserId(id));
    }

    // @SecurityRequirement(name = "Bearer Authentication")
    // @PreAuthorize(value = "hasAuthority('Role_Candidate')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(candidateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CandidateCreationDTO candidateCreationDTO) {
        return new ResponseEntity<>(candidateService.create(candidateCreationDTO,
                null), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasAuthority('Role_Candidate')")
    @PutMapping(value = "/profile/personal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePersonalInfo(
            @RequestPart("candidateProfileDTO") String candidateProfileDTOJson,
            @RequestPart(value = "avatar", required = false) MultipartFile fileAvatar) {

        CandidateProfileDTO candidateProfileDTO = jsonReaderService.readValue(
                candidateProfileDTOJson, CandidateProfileDTO.class);
        return ResponseEntity.ok(candidateService.updatePersonalInfo(candidateProfileDTO, fileAvatar));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasAuthority('Role_Candidate')")
    @PutMapping(value = "/profile/job", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateJobInfo(
            @RequestPart("candidateProfileDTO") String candidateProfileDTOJson,
            @RequestPart(value = "cv", required = false) MultipartFile fileCV) {

        CandidateProfileDTO candidateProfileDTO = jsonReaderService.readValue(
                candidateProfileDTOJson, CandidateProfileDTO.class);
        return ResponseEntity.ok(candidateService.updateJobInfo(candidateProfileDTO, fileCV));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasAuthority('Role_Candidate')")
    @PutMapping("/searchable")
    public ResponseEntity<?> updateSearchable() {
        return ResponseEntity.ok(candidateService.updateSearchable());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasAuthority('Role_Candidate')")
    @PutMapping("/email-notification")
    public ResponseEntity<?> updateReceiveEmailNotification() {
        return ResponseEntity.ok(candidateService.updateMailReceive());
    }

    // @SecurityRequirement(name = "Bearer Authentication")
    // @PreAuthorize(value = "hasAuthority('Role_HR')")
    @PostMapping("/filter")
    public ResponseEntity<?> filterByHr(@Valid @RequestBody CandidateFilterByHRDTO candidateFilterByHRDTO,
                                        @Valid @RequestParam(defaultValue = PageDefault.NO) int no,
                                        @Valid @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(candidateService.filterByHR(candidateFilterByHRDTO, no, limit));
    }
}
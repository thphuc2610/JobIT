package com.r2s.findInternship.presentation.controller.hr;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateSearchDTO;
import com.r2s.findInternship.application.dto.hr.HROtherInfoDTO;
import com.r2s.findInternship.domain.service.JsonReaderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.hr.HRCreationDTO;
import com.r2s.findInternship.application.dto.hr.HRProfileDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.HRService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.HR)
public class HRController {
    private final HRService hrService;
    private final JsonReaderService<Object> jsonReaderService;

    // Get all
    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO) int no,
                                     @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(hrService.findAll(no, limit));
    }

    // Get one by id
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(hrService.findById(id));
    }

    // Get one by user id
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") long userId) {
        return ResponseEntity.ok(hrService.findByUserId(userId));
    }

    // Sign up
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> register(@Valid @RequestBody HRCreationDTO hrCreationDTO,
                                      @RequestBody MultipartFile fileAvatar) {
        return new ResponseEntity<>(hrService.register(hrCreationDTO, fileAvatar), HttpStatus.CREATED);
    }

    // Update information for HR
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping(value = "/profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateHRInfo(
            @Valid @RequestPart(name = "hrProfileDTO") String hrProfileDTOJson,
            @RequestPart(name = "fileAvatar", required = false) MultipartFile fileAvatar) {

        HRProfileDTO hrProfileDTO = jsonReaderService.readValue(hrProfileDTOJson, HRProfileDTO.class);

        return ResponseEntity.ok(hrService.updateHRInfo(hrProfileDTO, fileAvatar));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping(value = "/info-company", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateHRCompanyInfo(
            @Valid @RequestPart(name = "hrOtherInfoDTO") String hrOtherInfoDTOJson,
            @RequestPart(name = "fileLogo") MultipartFile fileLogo) {

        HROtherInfoDTO hrOtherInfoDTO = jsonReaderService.readValue(hrOtherInfoDTOJson, HROtherInfoDTO.class);

        return ResponseEntity.ok(hrService.updateHRCompanyInfo(hrOtherInfoDTO, fileLogo));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping("/email-notification")
    public ResponseEntity<?> updateReceiveEmailNotification() {
        return ResponseEntity.ok(hrService.updateReceiveEmail());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/search-candidate")
    public ResponseEntity<PaginationDTO> searchCandidates(
            @RequestParam(required = false) String desiredJob,
            @RequestParam(required = false) String desiredWorkingProvince,
            @RequestParam(required = false) List<Long> positionIds,
            @RequestParam(required = false) List<Long> majorIds,
            @RequestParam(required = false) List<Long> scheduleIds,
            @RequestParam(defaultValue = PageDefault.NO) int no,
            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {

        return ResponseEntity.ok(hrService.searchCandidates(
                new CandidateSearchDTO(desiredJob, desiredWorkingProvince, positionIds, majorIds, scheduleIds), no, limit));
    }
}
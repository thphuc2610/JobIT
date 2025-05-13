package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.JsonReaderService;
import com.r2s.findInternship.infracstructure.persistence.jpa.InternshipProgrammeServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.INTERNSHIP_PROGRAMME)
public class InternshipProgrammeController {
    private final InternshipProgrammeServiceImpl internshipProgrammeService;
    private final JsonReaderService<Object> jsonReaderService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @PostMapping
    public ResponseEntity<?> created(@RequestParam("file") MultipartFile file,
                                     @RequestParam("internshipProgrammeDTO") String internshipProgrammeDTOJson) {
        InternshipProgrammeDTO internshipProgrammeDTO = jsonReaderService
                .readValue(internshipProgrammeDTOJson, InternshipProgrammeDTO.class);

        return ResponseEntity.ok(internshipProgrammeService.create(internshipProgrammeDTO, file));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam("internshipProgrammeDTO") String internshipProgrammeDTOJson) {
        InternshipProgrammeDTO internshipProgrammeDTO = jsonReaderService
                .readValue(internshipProgrammeDTOJson, InternshipProgrammeDTO.class);
        return ResponseEntity.ok(internshipProgrammeService.update(internshipProgrammeDTO, file, id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @PutMapping("status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam("open") boolean open) {
        return ResponseEntity.ok(internshipProgrammeService.updateStatus(id, open));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Long id) {
        return ResponseEntity.ok(internshipProgrammeService.deleteById(id));
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/active")
    public ResponseEntity<?> findAllActive(@RequestParam(defaultValue = PageDefault.NO) int no,
                                           @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.internshipProgrammeService.findAllActive(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/active/university/{id}")
    public ResponseEntity<?> findAllActiveByUniversityId(@PathVariable("id") Long companyId,
                                                         @RequestParam(defaultValue = PageDefault.NO) int no,
                                                         @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.internshipProgrammeService.findAllActiveByUniversityId(companyId, no, limit));
    }
}
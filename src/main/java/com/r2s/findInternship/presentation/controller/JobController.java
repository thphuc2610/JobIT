package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.service.JsonReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.r2s.findInternship.domain.common.util.ExcelHelper;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.JobService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.JOB)
public class JobController {
    private final JobService jobService;
    private final JsonReaderService<Object> jsonReaderService;

    // Get
    @GetMapping("/company/{companyId}")
    public ResponseEntity<?> getJobsByCompany(@PathVariable Long companyId,
                                              @RequestParam(defaultValue = PageDefault.NO) int no,
                                              @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(jobService.findByCompanyId(companyId, no, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(jobService.findById(id));
    }

    @GetMapping 
    public ResponseEntity<?> findByAll(@RequestParam(defaultValue = PageDefault.NO) int no,
                                       @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(jobService.findByAll(no, limit));
    }

    @GetMapping("/active")
    public ResponseEntity<?> findByAllActive(@RequestParam(defaultValue = PageDefault.NO) int no,
                                             @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(jobService.findByAllActive(no, limit));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterJobs(@ModelAttribute JobFilterDTO jobFilterDTO,
                                        @RequestParam(defaultValue = PageDefault.NO) int page,
                                        @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {

        return ResponseEntity.ok(jobService.filterJob(jobFilterDTO, page, limit));
    }

    @GetMapping("/active-today-count")
    public ResponseEntity<?> countJobsByStatusOnDate(
            @RequestParam Boolean isStatus,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(jobService.countJobsByStatusOnDate(isStatus, date));
    }

    @GetMapping("/month-count")
    public ResponseEntity<?> countJobInMonth(@RequestParam int month,
                                             @RequestParam int year) {
        return ResponseEntity.ok(jobService.countJobsInMonth(month, year));
    }

    @GetMapping("/position-job")
    public ResponseEntity<?> countJobByPositionInMonth(@RequestParam int month,
                                                       @RequestParam int year) {
        return ResponseEntity.ok(jobService.countJobsByPositionInMonth(month, year));
    }

    // Post
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createdRecruitment(@RequestPart("jobCreationDTO") String jobCreationJson) {
        JobCreationDTO jobCreationDTO = jsonReaderService.readValue(jobCreationJson, JobCreationDTO.class);
        return ResponseEntity.ok(jobService.createJob(jobCreationDTO));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping("/duplicate/{id}")
    public ResponseEntity<?> duplicateJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.duplicateJob(id));
    }

    // Put
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id,
                                    @RequestPart("jobUpdateDTO") String jobUpdateJson) {
        JobDTO jobDTO = jsonReaderService.readValue(jobUpdateJson, JobDTO.class);
        return ResponseEntity.ok(this.jobService.updateJob(id, jobDTO));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping("/status/{id}")
    public MessageResponse jobStatus(@PathVariable Long id, @RequestParam Boolean isStatus) {
        return jobService.jobStatus(id, isStatus);
    }

    // Delete
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.deleteById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping("/excel")
    public ResponseEntity<?> create(@RequestPart("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                return new ResponseEntity<>(this.jobService.createByExcelFile(file), HttpStatus.CREATED);

            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.UniversityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiURL.UNIVERSITY)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<?> getAllUniversities() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UniversityDTO universityDTO, MultipartFile fileAvatar) {
        return ResponseEntity.ok(this.universityService.create(universityDTO, fileAvatar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.universityService.deleteById(id));
    }
}
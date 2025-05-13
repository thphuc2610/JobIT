package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.MajorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiURL.MAJOR)
@CrossOrigin(origins = "*", maxAge = 3600)
public class MajorController {
    private final MajorService majorService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.majorService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody MajorDTO majorDTO) {
        return ResponseEntity.ok(this.majorService.create(majorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.majorService.deleteById(id));
    }
}
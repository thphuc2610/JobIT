package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.PositionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiURL.POSITION)
@CrossOrigin(origins = "*", maxAge = 3600)
public class PositionController {
    private final PositionService positionService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.positionService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PositionDTO positionDTO) {
        return ResponseEntity.ok(this.positionService.create(positionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.positionService.deleteById(id));
    }
}
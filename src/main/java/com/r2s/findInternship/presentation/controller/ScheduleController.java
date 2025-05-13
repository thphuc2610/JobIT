package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.PositionService;
import com.r2s.findInternship.domain.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiURL.SCHEDULE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.scheduleService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(this.scheduleService.create(scheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.scheduleService.deleteById(id));
    }
}
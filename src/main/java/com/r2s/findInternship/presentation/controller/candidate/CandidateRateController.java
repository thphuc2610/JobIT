package com.r2s.findInternship.presentation.controller.candidate;

import com.r2s.findInternship.application.dto.RateDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.RateService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE_RATE)
public class CandidateRateController {
    private final RateService rateService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.rateService.findById(id));
    }

    @GetMapping("?companyId={id}&&username={username}")
    public ResponseEntity<?> findByCompanyIdAndUsername(@PathVariable("id") int companyId,
            @PathVariable String username) {
        return ResponseEntity.ok(this.rateService.findByCompanyIdAndUsername(companyId, username));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.rateService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<?> findAllActive() {
        return ResponseEntity.ok(this.rateService.findAllActive());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> findAllByCompanyId(@PathVariable("id") int companyId,
            @RequestParam int no, @RequestParam int limit) {
        return ResponseEntity.ok(this.rateService.findAllByCompanyId(companyId, no, limit));
    }

    @GetMapping("/company/{id}/user/{username}/active")
    public ResponseEntity<?> findByCompanyIdAndUsernameActive(@PathVariable("id") int companyId,
            @PathVariable("username") String username) {
        return ResponseEntity.ok(this.rateService.findActiveByUsernameAndCompanyId(companyId, username));
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody RateDTO rateDTO) {
        return new ResponseEntity<RateDTO>(this.rateService.create(rateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody RateDTO rateDTO) {
        return ResponseEntity.ok(this.rateService.update(id, rateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.rateService.deleteById(id));
    }
}
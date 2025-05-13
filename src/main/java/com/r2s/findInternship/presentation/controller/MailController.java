package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.MailService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequiredArgsConstructor
@RequestMapping(ApiURL.MAIL)
public class MailController {
    private final MailService mailService;

    @GetMapping("/active-user")
    public ResponseEntity<?> sendMailActive(@RequestParam String email) {
        return ResponseEntity.ok(mailService.sendMailActive(email));
    }
}
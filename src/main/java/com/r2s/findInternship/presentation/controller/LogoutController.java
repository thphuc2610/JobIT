package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiURL.LOGOUT)
public class LogoutController {
    private final InvalidTokenService invalidTokenService;

    @PostMapping
    public ResponseEntity<?> logout(@RequestParam("token") String token) {
        return ResponseEntity.ok(invalidTokenService.logout(token));
    }
}
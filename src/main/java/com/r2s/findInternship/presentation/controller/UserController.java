package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.application.dto.ChangePasswordByOtpDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.r2s.findInternship.application.dto.ChangePasswordDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.constant.PageDefault;
import com.r2s.findInternship.domain.service.MailService;
import com.r2s.findInternship.domain.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.USER)
public class UserController {
    private final UserService userService;
    private final MailService mailService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmailExists(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    @GetMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email,
                                            @RequestParam(value = "flag", defaultValue = "false") boolean flag) { // android: true
        return ResponseEntity.ok(mailService.sendMailForgotPassword(email, flag));
    }

    @GetMapping("/active-email")
    public ResponseEntity<?> sendMailActiveAndroid(@RequestParam String email) {
        return ResponseEntity.ok(mailService.sendMailActiveAndroid(email));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO passwordChangeDTO) {
        this.userService.changePassword(passwordChangeDTO);
        return ResponseEntity.ok(new MessageResponse(HttpServletResponse.SC_OK, "Đổi mật khẩu thành công", request.getServletPath()));
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String otp) {
        return ResponseEntity.ok(userService.verifyOtp(otp));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordByOtpDTO resetPasswordDTO) {
        return ResponseEntity.ok(userService.resetPassword(resetPasswordDTO));
    }


//    @PostMapping("/change-password-by-otp")
//    public ResponseEntity<?> changePasswordByOtp(@Valid @RequestBody ChangePasswordByOtpDTO changePasswordByOtpDTO) {
//        this.userService.changePasswordByOtp(changePasswordByOtpDTO);
//        return ResponseEntity.ok(new MessageResponse(HttpServletResponse.SC_OK, "Đổi mật khẩu thành công", request.getServletPath()));
//    }
    //    OTP ADD 2024/11/10 PhucHT END

    @GetMapping("/{username}")
    public ResponseEntity<?> findAllByUsernameLike(@PathVariable String username,
                                                   @RequestParam(defaultValue = PageDefault.NO) int no,
                                                   @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(userService.findAllByEmailLike(username, no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateMailReceive(@PathVariable long id) {
        return ResponseEntity.ok(userService.updateMailReceive(id));
    }
}
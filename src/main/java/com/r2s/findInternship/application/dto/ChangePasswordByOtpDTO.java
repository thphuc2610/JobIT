package com.r2s.findInternship.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordByOtpDTO implements Serializable {
    private String resetToken;
    @NotNull(message = "error.userNewPassword")
    private String newPassword;
    @NotNull(message = "error.userConfirmPassword")
    private String confirmPassword;
}
package com.r2s.findInternship.application.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO implements Serializable {
	@NotNull(message = "error.userOldPassword")
	private String oldPassword;
	@NotNull(message = "error.userNewPassword")
	private String newPassword;
	@NotNull(message = "error.userConfirmPassword")
	private String confirmPassword;
}
package com.r2s.findInternship.application.dto;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO implements Serializable {
	private String email;
	private String password;
}
package com.r2s.findInternship.application.dto;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO implements Serializable {
	private String token;
	private String type = "Bearer";
	private String email;
	private String role;
	private String avatar;
	private long idUser;
	private String message;

	public JwtResponseDTO(String accessToken, String email, String roles, String avatar, long id,
			String message) {
		this.idUser = id;
		this.token = accessToken;
		this.avatar = avatar;
		this.email = email;
		this.role = roles;
		this.message = message;
	}
}
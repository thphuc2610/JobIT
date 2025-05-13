package com.r2s.findInternship.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class User extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender")
	private Boolean gender;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "phone")
	private String phone;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birthday")
	private Date birthDay;

	@Column(name = "city")
	private String city;

	@Column(name = "district")
	private String district;

	@Column(name = "location")
	private String location;

	@Column(name = "mail_receive")
	private boolean mailReceive;

	@JsonIgnore
	@Column(name = "auth_provider")
	private String authProvider;

	@JsonIgnore
	@Column(name = "password_forgot_token")
	private String passwordForgotToken;

	@JsonIgnore
	@Column(name = "token_active")
	private String tokenActive;

	@JsonIgnore
	@Column(name = "password_forgot_otp")
	private String passwordForgotOtp;

	@JsonIgnore
	@Column(name = "otp_active")
	private String otpActive;

	@JsonIgnore
	@Column(name = "otp_expiry_date")
	private LocalDateTime otpExpiryDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
}
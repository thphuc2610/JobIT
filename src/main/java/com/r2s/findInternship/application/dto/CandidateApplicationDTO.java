package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.domain.constant.Constant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CandidateApplicationDTO implements Serializable {
	private Long id;
	private JobDTO jobDTO;
	private CandidateDTO candidateDTO;
	@JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
	private Date appliedDate;
	private String CV;
	private String referenceLetter;
	private String email;
	private String fullName;
	private String phone;
}
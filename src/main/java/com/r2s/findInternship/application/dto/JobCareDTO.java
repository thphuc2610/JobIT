package com.r2s.findInternship.application.dto;

import java.io.Serializable;

import com.r2s.findInternship.application.dto.candidate.CandidateDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCareDTO implements Serializable {
	private Long id;
	private JobDTO jobDTO;
	private CandidateDTO candidateDTO;
}
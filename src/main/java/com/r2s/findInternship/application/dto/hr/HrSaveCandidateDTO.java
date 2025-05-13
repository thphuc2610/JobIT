package com.r2s.findInternship.application.dto.hr;

import java.io.Serializable;

import com.r2s.findInternship.application.dto.candidate.CandidateDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HrSaveCandidateDTO implements Serializable{
    private Long id;
    private CandidateDTO candidateDTO;
    private HRDTO hrDTO;
}
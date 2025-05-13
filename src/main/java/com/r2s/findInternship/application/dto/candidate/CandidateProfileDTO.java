package com.r2s.findInternship.application.dto.candidate;

import com.r2s.findInternship.application.dto.user.UserProfileDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateProfileDTO {
    @NotNull(message = "The candidate profile must not be null")
    private UserProfileDTO userProfileDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}
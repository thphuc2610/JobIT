package com.r2s.findInternship.application.dto.candidate;

import com.r2s.findInternship.application.dto.user.UserCreationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateCreationDTO {
    @NotNull(message = "The user's information must not be null")
    private UserCreationDTO userCreationDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}
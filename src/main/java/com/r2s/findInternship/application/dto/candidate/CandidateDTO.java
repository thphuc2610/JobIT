package com.r2s.findInternship.application.dto.candidate;

import com.r2s.findInternship.application.dto.user.UserDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDTO {
    private Long id;
    private UserDTO userDTO;
    private CandidateOtherInfoDTO candidateOtherInfoDTO;
}
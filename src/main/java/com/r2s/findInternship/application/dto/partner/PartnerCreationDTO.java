package com.r2s.findInternship.application.dto.partner;

import com.r2s.findInternship.application.dto.user.UserCreationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerCreationDTO {
    @NotNull(message = "The user's information must not be null")
    private UserCreationDTO userCreationDTO;
    @NotNull(message = "The partner's other information must not be null")
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}
package com.r2s.findInternship.application.dto.partner;

import com.r2s.findInternship.application.dto.user.UserProfileDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerProfileDTO {
    @NotNull(message = "The partner's profile must not be null")
    private UserProfileDTO userProfileDTO;
    @NotNull(message = "The partner's other information must not be null")
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}
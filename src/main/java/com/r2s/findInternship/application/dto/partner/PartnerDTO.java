package com.r2s.findInternship.application.dto.partner;

import com.r2s.findInternship.application.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartnerDTO {
    private Long id;
    private UserDTO userDTO;
    private PartnerOtherInfoDTO partnerOtherInfoDTO;
}
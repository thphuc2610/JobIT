package com.r2s.findInternship.application.dto.hr;

import com.r2s.findInternship.application.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HRDTO {
    private Long id;
    private UserDTO userDTO;
    private HROtherInfoDTO hrOtherInfoDTO;
}
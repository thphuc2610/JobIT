package com.r2s.findInternship.application.dto.hr;

import com.r2s.findInternship.application.dto.user.UserCreationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HRCreationDTO {
    @NotNull(message = "The user's information must not be null")
    private UserCreationDTO userCreationDTO;
    @NotNull(message = "The hr's other information must not be null")
    private HROtherInfoDTO hrOtherInfoDTO;
}
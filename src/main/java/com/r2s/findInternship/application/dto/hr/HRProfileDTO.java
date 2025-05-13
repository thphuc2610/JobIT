package com.r2s.findInternship.application.dto.hr;

import com.r2s.findInternship.application.dto.user.UserProfileDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HRProfileDTO {
    @NotNull(message = "The hr's profile must not be null")
    private UserProfileDTO userProfileDTO;
    @NotNull(message = "The hr's position must not be null")
    private String position;
}
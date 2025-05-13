package com.r2s.findInternship.application.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCreationDTO extends UserProfileDTO {
    @NotEmpty(message = "error.passwordNotNull")
    @Size(min = 6, message = "error.passwordRequire")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$", message = "error.passwordRegex")
    private String password;

    @NotEmpty(message = "error.passwordNotNull")
    @Size(min = 6, message = "error.passwordRequire")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$", message = "error.passwordMatch")
    private String confirmPassword;
}
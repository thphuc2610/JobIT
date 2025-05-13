package com.r2s.findInternship.application.dto.user;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.domain.constant.Constant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserProfileDTO implements Serializable {
    @Email(message = "error.emailFormat")
    @NotEmpty(message = "error.emailNotNull")
    private String email;
    private String firstName;
    private String lastName;
    private Boolean gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT)
    private Date birthDay;
    private String phone;
    private String avatar;
    private String city;
    private String district;
    private String location;
    private Boolean mailReceive;
}
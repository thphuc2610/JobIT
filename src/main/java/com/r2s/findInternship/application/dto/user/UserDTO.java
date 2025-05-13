package com.r2s.findInternship.application.dto.user;

import com.r2s.findInternship.application.dto.RoleDTO;
import com.r2s.findInternship.application.dto.StatusDTO;

import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDTO extends UserProfileDTO {
    private Long id;
    private RoleDTO roleDTO; 
    private StatusDTO statusDTO;
}
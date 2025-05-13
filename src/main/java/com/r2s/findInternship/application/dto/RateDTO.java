package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import com.r2s.findInternship.application.dto.user.UserDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private UserDTO userDTO;
    private CompanyDTO companyDTO;
    private int score;
    private String title;
    private String comment;
    private Date createdDate;
    private StatusDTO statusDTO;
    private boolean hide;
}
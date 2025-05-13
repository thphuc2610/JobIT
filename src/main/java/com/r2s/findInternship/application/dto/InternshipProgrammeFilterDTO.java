package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InternshipProgrammeFilterDTO implements Serializable {
    private String title;
    private String location;
    private Date startDate;
    private Date endDate;
    private Integer statusId;
}
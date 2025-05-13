package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostedJobOfCompanyFilterByHrDTO implements Serializable {
    private String quickSearch;
    private String provinceName;
    private Date endDate;
    private Integer statusId;
}
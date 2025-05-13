package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobFilterDTO implements Serializable {
    private String title;
    private List<Long> jobPositionIds;
    private List<Long> jobMajorIds;
    private List<Long> jobScheduleIds;
    private String address;
}
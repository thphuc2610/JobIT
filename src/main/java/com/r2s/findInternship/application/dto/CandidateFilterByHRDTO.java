package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CandidateFilterByHRDTO implements Serializable {
    private String desiredJob;
    private String desiredWorkingProvince;
    private List<Integer> scheduleIds;
    private List<Integer> positionIds;
    private List<Integer> majorIds;
}
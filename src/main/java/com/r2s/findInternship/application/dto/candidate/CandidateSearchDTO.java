package com.r2s.findInternship.application.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateSearchDTO {
    private String desiredJob;
    private String desiredWorkingProvince;
    private List<Long> positionIds;
    private List<Long> majorIds;
    private List<Long> scheduleIds;
}
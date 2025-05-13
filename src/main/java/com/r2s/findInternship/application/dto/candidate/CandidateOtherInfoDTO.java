package com.r2s.findInternship.application.dto.candidate;

import java.util.List;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateOtherInfoDTO {
    private UniversityDTO universityDTO;
    private String CV;
    private String referenceLetter;
    private List<PositionDTO> positionDTOs;
    private List<MajorDTO> majorDTOs;
    private List<ScheduleDTO> scheduleDTOs;
    private String desiredJob;
    private String desiredWorkingProvince;
    private Boolean searchable;
}
package com.r2s.findInternship.application.dto;

import lombok.Data;

@Data
public class InternshipScheduleDTO {
    private Long id;
    private ScheduleDTO scheduleDTO;
    private InternshipProgrammeDTO internshipProgrammeDTO;
}
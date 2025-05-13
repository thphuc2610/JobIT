package com.r2s.findInternship.application.dto;

import lombok.Data;

@Data
public class InternshipMajorDTO {
    private Long id;
    private MajorDTO majorDTO;
    private InternshipProgrammeDTO internshipProgrammeDTO;
}
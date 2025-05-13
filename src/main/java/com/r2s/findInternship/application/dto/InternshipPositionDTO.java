package com.r2s.findInternship.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipPositionDTO {
    private Long id;
    private PositionDTO positionDTO;
    private InternshipProgrammeDTO internshipProgrammeDTO;
}
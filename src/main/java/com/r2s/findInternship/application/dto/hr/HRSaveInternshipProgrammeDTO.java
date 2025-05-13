package com.r2s.findInternship.application.dto.hr;

import java.io.Serializable;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HRSaveInternshipProgrammeDTO implements Serializable {
    private Long id;
    private InternshipProgrammeDTO internshipProgrammeDTO;
    private HRDTO hrDTO;
}
package com.r2s.findInternship.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobMajorDTO {
    private Long id;
    private JobDTO jobDTO;
    private MajorDTO majorDTO;
}
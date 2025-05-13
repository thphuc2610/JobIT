package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobScheduleDTO implements Serializable {
    private Long id;
    private JobDTO jobDTO;
    private ScheduleDTO scheduleDTO;
}
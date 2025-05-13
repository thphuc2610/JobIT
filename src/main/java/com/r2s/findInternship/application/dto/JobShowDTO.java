package com.r2s.findInternship.application.dto;

import java.util.Date;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobShowDTO {
    private long id;
    private String title;
    private CompanyDTO companyDTO;
    private List<PositionDTO> positionDTOS;
    private List<MajorDTO> majorDTOS;
    private List<ScheduleDTO> scheduleDTOS;
    private int amount;
    private Date postingDate;
    private Date applicationDeadline;
    private Double minAllowance;
    private Double maxAllowance;
    private boolean noAllowance;
    private String description;
    private String requirements;
    private String benefits;
    private String country;
    private String city;
    private String district;
    private String address;
}
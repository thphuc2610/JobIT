package com.r2s.findInternship.application.dto.show;

import java.util.Date;
import java.util.List;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InternshipProgrammeDTOShow {
    private int id;
    private String title;
    private String recommendation;
    private Date startDate;
    private Date endDate;
    private String students;
    private Date createdDate;
    private Date lastModifiedDate;
    private List<PositionDTO> positionDTOs;
    private List<ScheduleDTO> scheduleDTOs;
    private List<MajorDTO> majorDTOs;
    private long amount;
    private StatusDTO statusDTO;
    private String location;
    private UniversityDTO universityDTO;
}
package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.r2s.findInternship.application.dto.hr.HRDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCreationDTO implements Serializable {
	private String title;
	private List<PositionDTO> positionDTOS;
	private List<MajorDTO> majorDTOS;
	private List<ScheduleDTO> scheduleDTOS;
	private int amount;
	private Date postingDate;
	private Date applicationDeadline;
	private Double minAllowance;
	private Double maxAllowance;
	private Boolean noAllowance;
	private String description;
	private String requirements;
	private String benefits;
	private String country;
	private String city;
	private String district;
	private String address;
}
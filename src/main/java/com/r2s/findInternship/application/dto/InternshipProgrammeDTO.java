package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.partner.PartnerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipProgrammeDTO implements Serializable {
	private int id;
	private String title;
	private List<MajorDTO> majorDTOs;
	private List<ScheduleDTO> scheduleDTOs;
	private String recommendation;
	private List<PositionDTO> positionDTOs;
	private Date startDate;
	private Date endDate;
	private Date updatedDate;
	private String students; // link files excel list of students
	private Date createdDate;
	private MultipartFile file;
	private StatusDTO statusDTO;
	private long amount;
	private String location;
	private UniversityDTO universityDTO;
}
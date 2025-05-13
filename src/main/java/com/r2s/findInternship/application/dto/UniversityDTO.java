package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDTO implements Serializable {
	private Long id;
	private String name;
	private String avatar;
	private String shortName;
	private String description;
	private String website;
	private String email;
	private String phone;
	private UniversityTypeDTO universityTypeDTO;
	private String location;
	private StatusDTO statusDTO;
	private Date createdDate;
}
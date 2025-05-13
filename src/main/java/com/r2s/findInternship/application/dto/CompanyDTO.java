package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.domain.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDTO implements Serializable {
	private Long id;
	private String logo;
	private String name;
	private String tax;
	private String email;
	private String phone;
	private String personnelSize;
	private String website;
	private String country;
	private String province;
	private String district;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT)
	private Date createdDate;
	private String location;
	private StatusDTO statusDTO;
	private String description;
}
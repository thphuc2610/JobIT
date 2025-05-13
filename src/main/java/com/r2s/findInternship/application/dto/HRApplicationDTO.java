package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import java.util.Date;

import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.domain.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HRApplicationDTO implements Serializable {
	private int id;
	private HRDTO hrDTO;
	private Date createdDate;
	private Date lastModifiedDate;
	private Status status;
	private String note;
}
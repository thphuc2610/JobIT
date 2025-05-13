package com.r2s.findInternship.application.dto.show;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.findInternship.domain.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationDTONotShowJob {
	private int id;
	private int candidateId;
	private String candidateFirstName;
	private String candidateLastName;
	private String candidatePhoneNumber;
	private String candidateEmail;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
	private Date createdDate;
	private String referenceLetter;
	private String CV;
}
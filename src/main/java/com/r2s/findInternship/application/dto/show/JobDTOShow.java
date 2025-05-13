package com.r2s.findInternship.application.dto.show;

import java.util.Date;

import com.r2s.findInternship.application.dto.StatusDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobDTOShow {
	private int id;
	private String name;
	private Date createdDate;
	private Date lastModifiedDate;
	private String workLocation;
	private Date endDate;
	private long amountOfAppliedCandidates;
	// private long views; -> update later
	private int hrId;
	private String hrFirstName;
	private String hrLastName;
	private StatusDTO statusDTO;
}
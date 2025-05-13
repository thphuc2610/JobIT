package com.r2s.findInternship.application.dto;

import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCandidateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer[] majorIds;
	private Integer[] jobTypeIds;
	private Integer[] jobPositionIds;
	private Integer desiredWorkingProvinceId;
	private String desiredJob;
}
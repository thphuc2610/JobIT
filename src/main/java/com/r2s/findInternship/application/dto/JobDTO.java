package com.r2s.findInternship.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Builder
@EqualsAndHashCode(callSuper = true)
public class JobDTO extends JobCreationDTO {
	private Long id;
	private StatusDTO statusDTO;
	private CompanyDTO companyDTO;
}
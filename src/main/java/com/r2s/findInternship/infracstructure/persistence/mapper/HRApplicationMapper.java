package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.HRApplicationDTO;
import com.r2s.findInternship.domain.entity.HRApplication;

@Mapper(componentModel = "spring")
public interface HRApplicationMapper {
	HRApplication toEntity(HRApplicationDTO hrApplicationDTO);

	HRApplicationDTO toDTO(HRApplication hrApplication);
}

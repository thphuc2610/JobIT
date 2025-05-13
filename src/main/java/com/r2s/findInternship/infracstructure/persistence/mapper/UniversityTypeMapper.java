package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.UniversityTypeDTO;
import com.r2s.findInternship.domain.entity.UniversityType;

@Mapper(componentModel = "spring")
public interface UniversityTypeMapper {
	UniversityType toEntity(UniversityTypeDTO universityTypeDTO);

	UniversityTypeDTO toDTO(UniversityType universityType);
}

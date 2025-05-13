package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.StatusDTO;
import com.r2s.findInternship.domain.entity.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
	Status toEntity(StatusDTO statusDTO);

	StatusDTO toDTO(Status status);
}

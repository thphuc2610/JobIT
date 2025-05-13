package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.*;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MajorMapper {

	Major toEntity(MajorDTO majorDTO);

	@Mapping(source = "major.id", target = "id")
	@Mapping(source = "major.name", target = "name")
	MajorDTO toMajorDTO(CandidateMajor candidateMajor);

	@Mapping(source = "major.id", target = "id")
	@Mapping(source = "major.name", target = "name")
	MajorDTO jobMajortoMajorDto(JobMajor jobMajor);

	@IterableMapping(elementTargetType = MajorDTO.class)
	List<MajorDTO> jobMajortoMajorDto(List<JobMajor> jobMajors);

	@Mapping(source = "major.id", target = "id")
	@Mapping(source = "major.name", target = "name")
	MajorDTO toMajorDto(InternshipMajor internshipMajor);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	MajorDTO toDTO(Major major);

}

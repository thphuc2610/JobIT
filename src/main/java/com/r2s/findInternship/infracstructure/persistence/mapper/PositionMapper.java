package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.CandidatePosition;
import com.r2s.findInternship.domain.entity.InternshipPosition;
import com.r2s.findInternship.domain.entity.JobPosition;
import com.r2s.findInternship.domain.entity.Position;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position toEntity(PositionDTO positionDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    PositionDTO toDTO(Position position);

    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO toPositionDTO(CandidatePosition candidatePosition);

    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO jobPositiontoPositionDto(JobPosition jobPosition);

    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO toPositionDto(InternshipPosition internshipPosition);

}

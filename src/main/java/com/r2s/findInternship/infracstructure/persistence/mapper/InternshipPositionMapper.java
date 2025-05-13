package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipPositionDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.domain.entity.InternshipMajor;
import com.r2s.findInternship.domain.entity.InternshipPosition;

import com.r2s.findInternship.domain.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { PositionMapper.class, InternshipPositionMapper.class })
public interface InternshipPositionMapper {
    InternshipPosition toEntity(InternshipPositionDTO jobPositionDemandDTO);

    @Mapping(source = "internshipProgramme.id", target = "id")
    @Mapping(source = "internshipProgramme.title", target = "title")
    @Mapping(source = "internshipProgramme.recommendation", target = "recommendation")
//    @Mapping(source = "internshipProgramme.requirement", target = "requirement")
//    @Mapping(source = "internshipProgramme.otherInfo", target = "otherInfo")
    @Mapping(source = "internshipProgramme.startDate", target = "startDate")
    @Mapping(source = "internshipProgramme.endDate", target = "endDate")
    @Mapping(source = "internshipProgramme.location", target = "location")
    @Mapping(source = "internshipProgramme.amount", target = "amount")
    @Mapping(source = "internshipProgramme.university", target = "universityDTO")
    InternshipProgrammeDTOShow toDTO(InternshipPosition internshipPosition);
}

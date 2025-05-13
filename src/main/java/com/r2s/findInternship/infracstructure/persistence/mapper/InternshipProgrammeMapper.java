package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {
                PartnerMapper.class,
                StatusMapper.class,
                PositionMapper.class,
                MajorMapper.class,
                ScheduleMapper.class
        })
public interface InternshipProgrammeMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "recommendation", target = "recommendation")
//    @Mapping(source = "requirement", target = "requirement")
//    @Mapping(source = "otherInfo", target = "otherInfo")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "students", target = "students")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "universityDTO", target = "university")
    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "positionDTOs", target = "internshipPositions")
    @Mapping(source = "scheduleDTOs", target = "internshipSchedules")
    @Mapping(source = "majorDTOs", target = "internshipMajors")
    InternshipProgramme toEntity(InternshipProgrammeDTO internshipProgrammeDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "recommendation", target = "recommendation")
//    @Mapping(source = "requirement", target = "requirement")
//    @Mapping(source = "otherInfo", target = "otherInfo")
    @Mapping(source = "students", target = "students")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "university", target = "universityDTO")
    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "internshipPositions", target = "positionDTOs")
    @Mapping(source = "internshipSchedules", target = "scheduleDTOs")
    @Mapping(source = "internshipMajors", target = "majorDTOs")
    InternshipProgrammeDTOShow toDTOShow(InternshipProgramme internshipProgramme);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "internshipPositions", source = "positionDTOs")
    @Mapping(target = "internshipMajors", source = "majorDTOs")
    @Mapping(target = "internshipSchedules", source = "scheduleDTOs")
    @Mapping(target = "students", source = "students")
    @Mapping(target = "recommendation", source = "recommendation")
//    @Mapping(target = "requirement", source = "requirement")
    void updateEntityFromDTO(InternshipProgrammeDTO universityDemandDTO, @MappingTarget InternshipProgramme existingEntity);
}
package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipScheduleDTO;
import com.r2s.findInternship.domain.entity.InternshipSchedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {ScheduleMapper.class, InternshipProgrammeMapper.class})
public interface InternshipScheduleMapper {

    InternshipSchedule toEntity(InternshipScheduleDTO jobTypeDemandDTO);

    @Mapping(source = "internshipProgramme", target = "internshipProgrammeDTO")
    @Mapping(source = "schedule", target = "scheduleDTO")
    InternshipScheduleDTO toDTO(InternshipSchedule jobTypeDemand);
}

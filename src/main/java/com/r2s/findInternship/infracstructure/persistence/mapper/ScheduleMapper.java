package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.CandidateSchedule;
import com.r2s.findInternship.domain.entity.InternshipSchedule;
import com.r2s.findInternship.domain.entity.JobSchedule;
import com.r2s.findInternship.domain.entity.Schedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

	Schedule toEntity(ScheduleDTO scheduleDTO);

	ScheduleDTO toDTO(Schedule schedule);

	@Mapping(source = "schedule.id", target = "id")
	@Mapping(source = "schedule.name", target = "name")
	ScheduleDTO toScheduleDTO(CandidateSchedule candidateSchedule);

	@Mapping(source = "schedule.id", target = "id")
	@Mapping(source = "schedule.name", target = "name")
	ScheduleDTO toDTO(JobSchedule jobSchedule);

	@Mapping(source = "schedule.id", target = "id")
	@Mapping(source = "schedule.name", target = "name")
	ScheduleDTO toDTO(InternshipSchedule internshipSchedule);

}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.*;

import com.r2s.findInternship.domain.entity.JobSchedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobScheduleMapper {
    @Mapping(source = "job", target = "jobDTO")
    @Mapping(source = "schedule", target = "scheduleDTO")
    JobScheduleDTO toDto(JobSchedule jobMajor);

    @Mapping(source = "schedule.id", target = "id")
    @Mapping(source = "schedule.name", target = "name")
    ScheduleDTO toScheduleDto(JobSchedule jobSchedule);

    @Mapping(source = "jobSchedule.job.id", target = "id")
    @Mapping(source = "jobSchedule.job.title", target = "title")
    @Mapping(source = "jobSchedule.job.description", target = "description")
    @Mapping(source = "jobSchedule.job.minAllowance", target = "minAllowance")
    @Mapping(source = "jobSchedule.job.maxAllowance", target = "maxAllowance")
    @Mapping(source = "jobSchedule.job.requirements", target = "requirements")
    @Mapping(source = "jobSchedule.job.benefits", target = "benefits")
    @Mapping(source = "jobSchedule.job.postingDate", target = "postingDate")
    @Mapping(source = "jobSchedule.job.applicationDeadline", target = "applicationDeadline")
    @Mapping(source = "jobSchedule.job.noAllowance", target = "noAllowance")
    @Mapping(source = "jobSchedule.job.country", target = "country")
    @Mapping(source = "jobSchedule.job.city", target = "city")
    @Mapping(source = "jobSchedule.job.district", target = "district")
    @Mapping(source = "jobSchedule.job.address", target = "address")
    @Mapping(source = "jobSchedule.job.amount", target = "amount")
    JobShowDTO toJobShowDto(JobSchedule jobSchedule);

    JobSchedule toEntity(JobScheduleDTO jobScheduleDTO);

    JobScheduleDTO toDTO(JobSchedule jobSchedule);
}
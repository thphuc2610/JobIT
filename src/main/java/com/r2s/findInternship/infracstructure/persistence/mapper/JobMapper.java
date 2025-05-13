package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.domain.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                HRMapper.class,
                PositionMapper.class,
                ScheduleMapper.class,
                MajorMapper.class,
                StatusMapper.class,
                CompanyMapper.class})
public interface JobMapper {
    @Mappings({
            @Mapping(source = "positionDTOS", target = "jobPositions"),
            @Mapping(source = "scheduleDTOS", target = "jobSchedules"),
            @Mapping(source = "majorDTOS", target = "jobMajors"),
    })
    Job toEntity(JobCreationDTO jobCreationDTO);

    @Mappings({
            @Mapping(source = "positionDTOS", target = "jobPositions"),
            @Mapping(source = "scheduleDTOS", target = "jobSchedules"),
            @Mapping(source = "majorDTOS", target = "jobMajors"),
            @Mapping(source = "statusDTO", target = "status")
    })
    Job jobUpdateDTOToJob(JobDTO jobDTO);

    @Mapping(source = "jobPositions", target = "positionDTOS")
    @Mapping(source = "jobSchedules", target = "scheduleDTOS")
    @Mapping(source = "jobMajors", target = "majorDTOS")
    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "hr.company", target = "companyDTO")
    JobDTO toDTOShow(Job job);

    @Mappings({
            @Mapping(source = "positionDTOS", target = "jobPositions"),
            @Mapping(source = "scheduleDTOS", target = "jobSchedules"),
            @Mapping(source = "majorDTOS", target = "jobMajors"),
            @Mapping(target = "id", ignore = true)
    })
    void updateJobFromJobDTO(JobDTO jobDTO, @MappingTarget Job existingEntity);

    List<JobShowDTO> toDtoList(List<Job> jobs);
}
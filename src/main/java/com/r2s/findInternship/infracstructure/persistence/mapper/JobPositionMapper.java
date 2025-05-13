package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobPositionDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.JobPosition;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobPositionMapper {
    @Mapping(source = "position.id", target = "id")
    @Mapping(source = "position.name", target = "name")
    PositionDTO toPositionDto(JobPosition jobPosition);

    @Mapping(source = "jobPosition.job.id", target = "id")
    @Mapping(source = "jobPosition.job.title", target = "title")
    @Mapping(source = "jobPosition.job.description", target = "description")
    @Mapping(source = "jobPosition.job.minAllowance", target = "minAllowance")
    @Mapping(source = "jobPosition.job.maxAllowance", target = "maxAllowance")
    @Mapping(source = "jobPosition.job.requirements", target = "requirements")
    @Mapping(source = "jobPosition.job.benefits", target = "benefits")
    @Mapping(source = "jobPosition.job.postingDate", target = "postingDate")
    @Mapping(source = "jobPosition.job.applicationDeadline", target = "applicationDeadline")
    @Mapping(source = "jobPosition.job.amount", target = "amount")
    @Mapping(source = "jobPosition.job.country", target = "country")
    @Mapping(source = "jobPosition.job.city", target = "city")
    @Mapping(source = "jobPosition.job.district", target = "district")
    @Mapping(source = "jobPosition.job.address", target = "address")
    JobShowDTO toJobShowDto(JobPosition jobPosition);


    JobPosition toEntity(JobPositionDTO jobPositionDTO);

    JobPositionDTO toDTO(JobPosition jobPosition);
}
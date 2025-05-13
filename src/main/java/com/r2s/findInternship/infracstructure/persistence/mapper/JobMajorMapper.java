package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobMajorDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.JobMajor;
import com.r2s.findInternship.domain.entity.Major;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMajorMapper {
    MajorDTO toMajorDto(Major major);

    @Mapping(source = "major.id", target = "id")
    @Mapping(source = "major.name", target = "name")
    MajorDTO toMajorDto(JobMajor jobMajor);

    @Mapping(source = "jobMajor.job.id", target = "id")
    @Mapping(source = "jobMajor.job.title", target = "title")
    @Mapping(source = "jobMajor.job.description", target = "description")
    @Mapping(source = "jobMajor.job.minAllowance", target = "minAllowance")
    @Mapping(source = "jobMajor.job.maxAllowance", target = "maxAllowance")
    @Mapping(source = "jobMajor.job.requirements", target = "requirements")
    @Mapping(source = "jobMajor.job.benefits", target = "benefits")
    @Mapping(source = "jobMajor.job.postingDate", target = "postingDate")
    @Mapping(source = "jobMajor.job.applicationDeadline", target = "applicationDeadline")
    @Mapping(source = "jobMajor.job.amount", target = "amount")
    @Mapping(source = "jobMajor.job.country", target = "country")
    @Mapping(source = "jobMajor.job.city", target = "city")
    @Mapping(source = "jobMajor.job.district", target = "district")
    @Mapping(source = "jobMajor.job.address", target = "address")
    JobShowDTO toJobDto(JobMajor jobMajor);

    @IterableMapping(elementTargetType = MajorDTO.class)
    List<MajorDTO> toMajorDtoList(List<JobMajor> jobMajors);

    JobMajor toEntity(JobMajorDTO jobMajorDTO);

    JobMajorDTO toDTO(JobMajor jobMajor);
}
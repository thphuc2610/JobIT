package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.hr.HRSaveInternshipProgrammeDTO;
import com.r2s.findInternship.domain.entity.HRSaveInternshipProgramme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InternshipProgrammeMapper.class, HRMapper.class})
public interface HRSaveInternshipProgrammeMapper {
    @Mapping(source = "hrDTO", target = "hr")
    @Mapping(source = "internshipProgrammeDTO", target = "internshipProgramme")
    HRSaveInternshipProgramme toEntity(HRSaveInternshipProgrammeDTO hrSaveInternshipProgrammeDTO);

    @Mapping(source = "hr", target = "hrDTO")
    @Mapping(source = "internshipProgramme", target = "internshipProgrammeDTO")
    HRSaveInternshipProgrammeDTO toDTO(HRSaveInternshipProgramme hrSaveInternshipProgramme);
}
package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.hr.HrSaveCandidateDTO;
import com.r2s.findInternship.domain.entity.HRSaveCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CandidateMapper.class)
public interface HRSaveCandidateMapper {
    @Mapping(source = "hrDTO", target = "hr")
    @Mapping(source = "candidateDTO", target = "candidate")
    HRSaveCandidate toEntity(HrSaveCandidateDTO hrSaveCandidateDTO);

    @Mapping(source = "hr", target = "hrDTO")
    @Mapping(source = "candidate", target = "candidateDTO")
    HrSaveCandidateDTO toDTO(HRSaveCandidate hrSaveCandidate);
}
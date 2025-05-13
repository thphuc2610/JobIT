package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import com.r2s.findInternship.application.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.domain.entity.Candidate;

@Mapper(componentModel = "spring", uses = { UserMapper.class, UniversityMapper.class,
        PositionMapper.class, ScheduleMapper.class, MajorMapper.class })
public interface CandidateMapper {

    @Mapping(source = "userCreationDTO", target = "user")
    @Mapping(source = "candidateOtherInfoDTO.searchable", target = "searchable")
    @Mapping(source = "candidateOtherInfoDTO.CV", target = "CV")
    @Mapping(source = "candidateOtherInfoDTO.referenceLetter", target = "referenceLetter")
    @Mapping(source = "candidateOtherInfoDTO.universityDTO", target = "university")
    @Mapping(source = "candidateOtherInfoDTO.desiredJob", target = "desiredJob")
    @Mapping(source = "candidateOtherInfoDTO.desiredWorkingProvince", target = "desiredWorkingProvince")
    Candidate toEntity(CandidateCreationDTO candidateCreationDTO);

    @Mapping(source = "userProfileDTO", target = "user")
    @Mapping(source = "candidateOtherInfoDTO.searchable", target = "searchable")
    @Mapping(source = "candidateOtherInfoDTO.CV", target = "CV")
    @Mapping(source = "candidateOtherInfoDTO.referenceLetter", target = "referenceLetter")
    @Mapping(source = "candidateOtherInfoDTO.universityDTO", target = "university")
    @Mapping(source = "candidateOtherInfoDTO.desiredJob", target = "desiredJob")
    @Mapping(source = "candidateOtherInfoDTO.desiredWorkingProvince", target = "desiredWorkingProvince")
    Candidate toEntity(CandidateProfileDTO candidateProfileDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "searchable", target = "candidateOtherInfoDTO.searchable")
    @Mapping(source = "CV", target = "candidateOtherInfoDTO.CV")
    @Mapping(source = "referenceLetter", target = "candidateOtherInfoDTO.referenceLetter")
    @Mapping(source = "university", target = "candidateOtherInfoDTO.universityDTO")
    @Mapping(source = "desiredJob", target = "candidateOtherInfoDTO.desiredJob")
    @Mapping(source = "desiredWorkingProvince", target = "candidateOtherInfoDTO.desiredWorkingProvince")
    @Mapping(source = "candidatePositions", target = "candidateOtherInfoDTO.positionDTOs")
    @Mapping(source = "candidateSchedules", target = "candidateOtherInfoDTO.scheduleDTOs")
    @Mapping(source = "candidateMajors", target = "candidateOtherInfoDTO.majorDTOs")
    CandidateDTO toDTO(Candidate candidate);

    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "candidateOtherInfoDTO.searchable", target = "searchable")
    @Mapping(source = "candidateOtherInfoDTO.CV", target = "CV")
    @Mapping(source = "candidateOtherInfoDTO.referenceLetter", target = "referenceLetter")
    @Mapping(source = "candidateOtherInfoDTO.universityDTO", target = "university")
    @Mapping(source = "candidateOtherInfoDTO.desiredJob", target = "desiredJob")
    @Mapping(source = "candidateOtherInfoDTO.desiredWorkingProvince", target = "desiredWorkingProvince")
    Candidate toEntity(CandidateDTO candidateDTO);
}
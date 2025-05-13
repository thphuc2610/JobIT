package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.application.dto.candidate.CandidateApplicationDTO;
import com.r2s.findInternship.application.dto.show.ApplicationDTONotShowCandidate;
import com.r2s.findInternship.application.dto.show.ApplicationDTONotShowJob;
import com.r2s.findInternship.domain.entity.CandidateApplication;

@Mapper(componentModel = "spring", uses = { JobMapper.class, CandidateMapper.class })
public interface CandidateApplicationMapper {

	@Mapping(source = "applicationDTO.jobDTO", target = "job")
	@Mapping(source = "applicationDTO.candidateDTO", target = "candidate")
	CandidateApplication toEntity(CandidateApplicationDTO applicationDTO);

	@Mapping(source = "job", target = "jobDTO")
	@Mapping(source = "candidate", target = "candidateDTO")
	@Mapping(source = "createdDate", target = "appliedDate")
	CandidateApplicationDTO toDTO(CandidateApplication application);

	@Mapping(source = "application.job", target = "jobDTO")
	ApplicationDTONotShowCandidate toDTONotShowCandidate(CandidateApplication application);

	@Mapping(source = "application.candidate.user.id", target = "candidateId")
	@Mapping(source = "application.candidate.user.firstName", target = "candidateFirstName")
	@Mapping(source = "application.candidate.user.lastName", target = "candidateLastName")
	@Mapping(source = "application.candidate.user.phone", target = "candidatePhoneNumber")
	@Mapping(source = "application.candidate.user.email", target = "candidateEmail")
	ApplicationDTONotShowJob toDTONotShowJob(CandidateApplication application);
}
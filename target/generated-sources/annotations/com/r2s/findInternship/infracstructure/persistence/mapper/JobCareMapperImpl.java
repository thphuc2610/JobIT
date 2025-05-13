package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobCareDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.JobCare;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:18+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class JobCareMapperImpl implements JobCareMapper {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public JobCare toEntity(JobCareDTO jobCareDTO) {
        if ( jobCareDTO == null ) {
            return null;
        }

        JobCare.JobCareBuilder jobCare = JobCare.builder();

        jobCare.candidate( candidateDTOToCandidate( jobCareDTO.getCandidateDTO() ) );
        jobCare.job( jobMapper.jobUpdateDTOToJob( jobCareDTO.getJobDTO() ) );
        if ( jobCareDTO.getId() != null ) {
            jobCare.id( jobCareDTO.getId() );
        }

        return jobCare.build();
    }

    @Override
    public JobCareDTO toDTO(JobCare jobCare) {
        if ( jobCare == null ) {
            return null;
        }

        JobCareDTO.JobCareDTOBuilder jobCareDTO = JobCareDTO.builder();

        jobCareDTO.jobDTO( jobMapper.toDTOShow( jobCare.getJob() ) );
        jobCareDTO.candidateDTO( candidateToCandidateDTO( jobCare.getCandidate() ) );
        jobCareDTO.id( jobCare.getId() );

        return jobCareDTO.build();
    }

    protected Candidate candidateDTOToCandidate(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        if ( candidateDTO.getId() != null ) {
            candidate.setId( candidateDTO.getId() );
        }

        return candidate;
    }

    protected CandidateDTO candidateToCandidateDTO(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateDTO.CandidateDTOBuilder candidateDTO = CandidateDTO.builder();

        candidateDTO.id( candidate.getId() );

        return candidateDTO.build();
    }
}

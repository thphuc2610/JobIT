package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.candidate.CandidateApplicationDTO;
import com.r2s.findInternship.application.dto.show.ApplicationDTONotShowCandidate;
import com.r2s.findInternship.application.dto.show.ApplicationDTONotShowJob;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidateApplication;
import com.r2s.findInternship.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CandidateApplicationMapperImpl implements CandidateApplicationMapper {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public CandidateApplication toEntity(CandidateApplicationDTO applicationDTO) {
        if ( applicationDTO == null ) {
            return null;
        }

        CandidateApplication.CandidateApplicationBuilder candidateApplication = CandidateApplication.builder();

        candidateApplication.job( jobMapper.jobUpdateDTOToJob( applicationDTO.getJobDTO() ) );
        candidateApplication.candidate( candidateMapper.toEntity( applicationDTO.getCandidateDTO() ) );
        if ( applicationDTO.getId() != null ) {
            candidateApplication.id( applicationDTO.getId() );
        }
        candidateApplication.email( applicationDTO.getEmail() );
        candidateApplication.fullName( applicationDTO.getFullName() );
        candidateApplication.phone( applicationDTO.getPhone() );
        candidateApplication.CV( applicationDTO.getCV() );
        candidateApplication.referenceLetter( applicationDTO.getReferenceLetter() );

        return candidateApplication.build();
    }

    @Override
    public CandidateApplicationDTO toDTO(CandidateApplication application) {
        if ( application == null ) {
            return null;
        }

        CandidateApplicationDTO.CandidateApplicationDTOBuilder candidateApplicationDTO = CandidateApplicationDTO.builder();

        candidateApplicationDTO.jobDTO( jobMapper.toDTOShow( application.getJob() ) );
        candidateApplicationDTO.candidateDTO( candidateMapper.toDTO( application.getCandidate() ) );
        candidateApplicationDTO.appliedDate( application.getCreatedDate() );
        candidateApplicationDTO.id( application.getId() );
        candidateApplicationDTO.CV( application.getCV() );
        candidateApplicationDTO.referenceLetter( application.getReferenceLetter() );
        candidateApplicationDTO.email( application.getEmail() );
        candidateApplicationDTO.fullName( application.getFullName() );
        candidateApplicationDTO.phone( application.getPhone() );

        return candidateApplicationDTO.build();
    }

    @Override
    public ApplicationDTONotShowCandidate toDTONotShowCandidate(CandidateApplication application) {
        if ( application == null ) {
            return null;
        }

        ApplicationDTONotShowCandidate applicationDTONotShowCandidate = new ApplicationDTONotShowCandidate();

        applicationDTONotShowCandidate.setJobDTO( jobMapper.toDTOShow( application.getJob() ) );
        applicationDTONotShowCandidate.setId( (int) application.getId() );
        applicationDTONotShowCandidate.setCreatedDate( application.getCreatedDate() );
        applicationDTONotShowCandidate.setReferenceLetter( application.getReferenceLetter() );
        applicationDTONotShowCandidate.setCV( application.getCV() );

        return applicationDTONotShowCandidate;
    }

    @Override
    public ApplicationDTONotShowJob toDTONotShowJob(CandidateApplication application) {
        if ( application == null ) {
            return null;
        }

        ApplicationDTONotShowJob applicationDTONotShowJob = new ApplicationDTONotShowJob();

        applicationDTONotShowJob.setCandidateId( (int) applicationCandidateUserId( application ) );
        applicationDTONotShowJob.setCandidateFirstName( applicationCandidateUserFirstName( application ) );
        applicationDTONotShowJob.setCandidateLastName( applicationCandidateUserLastName( application ) );
        applicationDTONotShowJob.setCandidatePhoneNumber( applicationCandidateUserPhone( application ) );
        applicationDTONotShowJob.setCandidateEmail( applicationCandidateUserEmail( application ) );
        applicationDTONotShowJob.setId( (int) application.getId() );
        applicationDTONotShowJob.setCreatedDate( application.getCreatedDate() );
        applicationDTONotShowJob.setReferenceLetter( application.getReferenceLetter() );
        applicationDTONotShowJob.setCV( application.getCV() );

        return applicationDTONotShowJob;
    }

    private long applicationCandidateUserId(CandidateApplication candidateApplication) {
        if ( candidateApplication == null ) {
            return 0L;
        }
        Candidate candidate = candidateApplication.getCandidate();
        if ( candidate == null ) {
            return 0L;
        }
        User user = candidate.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private String applicationCandidateUserFirstName(CandidateApplication candidateApplication) {
        if ( candidateApplication == null ) {
            return null;
        }
        Candidate candidate = candidateApplication.getCandidate();
        if ( candidate == null ) {
            return null;
        }
        User user = candidate.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String applicationCandidateUserLastName(CandidateApplication candidateApplication) {
        if ( candidateApplication == null ) {
            return null;
        }
        Candidate candidate = candidateApplication.getCandidate();
        if ( candidate == null ) {
            return null;
        }
        User user = candidate.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String applicationCandidateUserPhone(CandidateApplication candidateApplication) {
        if ( candidateApplication == null ) {
            return null;
        }
        Candidate candidate = candidateApplication.getCandidate();
        if ( candidate == null ) {
            return null;
        }
        User user = candidate.getUser();
        if ( user == null ) {
            return null;
        }
        String phone = user.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String applicationCandidateUserEmail(CandidateApplication candidateApplication) {
        if ( candidateApplication == null ) {
            return null;
        }
        Candidate candidate = candidateApplication.getCandidate();
        if ( candidate == null ) {
            return null;
        }
        User user = candidate.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}

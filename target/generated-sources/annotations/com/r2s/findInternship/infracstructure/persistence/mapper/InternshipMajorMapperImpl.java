package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipMajorDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.domain.entity.InternshipMajor;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.Major;
import com.r2s.findInternship.domain.entity.University;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class InternshipMajorMapperImpl implements InternshipMajorMapper {

    @Override
    public InternshipMajor toEntity(InternshipMajorDTO majorDemandDTO) {
        if ( majorDemandDTO == null ) {
            return null;
        }

        InternshipMajor internshipMajor = new InternshipMajor();

        if ( majorDemandDTO.getId() != null ) {
            internshipMajor.setId( majorDemandDTO.getId() );
        }

        return internshipMajor;
    }

    @Override
    public MajorDTO toMajorDto(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( internshipMajorMajorId( internshipMajor ) );
        majorDTO.name( internshipMajorMajorName( internshipMajor ) );

        return majorDTO.build();
    }

    @Override
    public InternshipProgrammeDTOShow toDTO(InternshipMajor majorDemand) {
        if ( majorDemand == null ) {
            return null;
        }

        InternshipProgrammeDTOShow internshipProgrammeDTOShow = new InternshipProgrammeDTOShow();

        internshipProgrammeDTOShow.setId( (int) majorDemandInternshipProgrammeId( majorDemand ) );
        internshipProgrammeDTOShow.setTitle( majorDemandInternshipProgrammeTitle( majorDemand ) );
        internshipProgrammeDTOShow.setRecommendation( majorDemandInternshipProgrammeRecommendation( majorDemand ) );
        internshipProgrammeDTOShow.setStartDate( majorDemandInternshipProgrammeStartDate( majorDemand ) );
        internshipProgrammeDTOShow.setEndDate( majorDemandInternshipProgrammeEndDate( majorDemand ) );
        internshipProgrammeDTOShow.setLocation( majorDemandInternshipProgrammeLocation( majorDemand ) );
        internshipProgrammeDTOShow.setAmount( majorDemandInternshipProgrammeAmount( majorDemand ) );
        internshipProgrammeDTOShow.setUniversityDTO( universityToUniversityDTO( majorDemandInternshipProgrammeUniversity( majorDemand ) ) );

        return internshipProgrammeDTOShow;
    }

    private int internshipMajorMajorId(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return 0;
        }
        Major major = internshipMajor.getMajor();
        if ( major == null ) {
            return 0;
        }
        int id = major.getId();
        return id;
    }

    private String internshipMajorMajorName(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        Major major = internshipMajor.getMajor();
        if ( major == null ) {
            return null;
        }
        String name = major.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private long majorDemandInternshipProgrammeId(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return 0L;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return 0L;
        }
        long id = internshipProgramme.getId();
        return id;
    }

    private String majorDemandInternshipProgrammeTitle(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        String title = internshipProgramme.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String majorDemandInternshipProgrammeRecommendation(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        String recommendation = internshipProgramme.getRecommendation();
        if ( recommendation == null ) {
            return null;
        }
        return recommendation;
    }

    private Date majorDemandInternshipProgrammeStartDate(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        Date startDate = internshipProgramme.getStartDate();
        if ( startDate == null ) {
            return null;
        }
        return startDate;
    }

    private Date majorDemandInternshipProgrammeEndDate(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        Date endDate = internshipProgramme.getEndDate();
        if ( endDate == null ) {
            return null;
        }
        return endDate;
    }

    private String majorDemandInternshipProgrammeLocation(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        String location = internshipProgramme.getLocation();
        if ( location == null ) {
            return null;
        }
        return location;
    }

    private long majorDemandInternshipProgrammeAmount(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return 0L;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return 0L;
        }
        long amount = internshipProgramme.getAmount();
        return amount;
    }

    private University majorDemandInternshipProgrammeUniversity(InternshipMajor internshipMajor) {
        if ( internshipMajor == null ) {
            return null;
        }
        InternshipProgramme internshipProgramme = internshipMajor.getInternshipProgramme();
        if ( internshipProgramme == null ) {
            return null;
        }
        University university = internshipProgramme.getUniversity();
        if ( university == null ) {
            return null;
        }
        return university;
    }

    protected UniversityDTO universityToUniversityDTO(University university) {
        if ( university == null ) {
            return null;
        }

        UniversityDTO.UniversityDTOBuilder universityDTO = UniversityDTO.builder();

        universityDTO.id( university.getId() );
        universityDTO.name( university.getName() );
        universityDTO.avatar( university.getAvatar() );
        universityDTO.shortName( university.getShortName() );
        universityDTO.description( university.getDescription() );
        universityDTO.website( university.getWebsite() );
        universityDTO.email( university.getEmail() );
        universityDTO.phone( university.getPhone() );
        universityDTO.location( university.getLocation() );
        universityDTO.createdDate( university.getCreatedDate() );

        return universityDTO.build();
    }
}

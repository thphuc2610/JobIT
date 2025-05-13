package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.CandidateMajor;
import com.r2s.findInternship.domain.entity.InternshipMajor;
import com.r2s.findInternship.domain.entity.JobMajor;
import com.r2s.findInternship.domain.entity.Major;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:18+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class MajorMapperImpl implements MajorMapper {

    @Override
    public Major toEntity(MajorDTO majorDTO) {
        if ( majorDTO == null ) {
            return null;
        }

        Major.MajorBuilder major = Major.builder();

        major.id( majorDTO.getId() );
        major.name( majorDTO.getName() );

        return major.build();
    }

    @Override
    public MajorDTO toMajorDTO(CandidateMajor candidateMajor) {
        if ( candidateMajor == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( candidateMajorMajorId( candidateMajor ) );
        majorDTO.name( candidateMajorMajorName( candidateMajor ) );

        return majorDTO.build();
    }

    @Override
    public MajorDTO jobMajortoMajorDto(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( jobMajorMajorId( jobMajor ) );
        majorDTO.name( jobMajorMajorName( jobMajor ) );

        return majorDTO.build();
    }

    @Override
    public List<MajorDTO> jobMajortoMajorDto(List<JobMajor> jobMajors) {
        if ( jobMajors == null ) {
            return null;
        }

        List<MajorDTO> list = new ArrayList<MajorDTO>( jobMajors.size() );
        for ( JobMajor jobMajor : jobMajors ) {
            list.add( jobMajortoMajorDto( jobMajor ) );
        }

        return list;
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
    public MajorDTO toDTO(Major major) {
        if ( major == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( major.getId() );
        majorDTO.name( major.getName() );

        return majorDTO.build();
    }

    private int candidateMajorMajorId(CandidateMajor candidateMajor) {
        if ( candidateMajor == null ) {
            return 0;
        }
        Major major = candidateMajor.getMajor();
        if ( major == null ) {
            return 0;
        }
        int id = major.getId();
        return id;
    }

    private String candidateMajorMajorName(CandidateMajor candidateMajor) {
        if ( candidateMajor == null ) {
            return null;
        }
        Major major = candidateMajor.getMajor();
        if ( major == null ) {
            return null;
        }
        String name = major.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int jobMajorMajorId(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return 0;
        }
        Major major = jobMajor.getMajor();
        if ( major == null ) {
            return 0;
        }
        int id = major.getId();
        return id;
    }

    private String jobMajorMajorName(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Major major = jobMajor.getMajor();
        if ( major == null ) {
            return null;
        }
        String name = major.getName();
        if ( name == null ) {
            return null;
        }
        return name;
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
}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HRSaveInternshipProgrammeDTO;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.entity.HRSaveInternshipProgramme;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class HRSaveInternshipProgrammeMapperImpl implements HRSaveInternshipProgrammeMapper {

    @Autowired
    private InternshipProgrammeMapper internshipProgrammeMapper;
    @Autowired
    private HRMapper hRMapper;

    @Override
    public HRSaveInternshipProgramme toEntity(HRSaveInternshipProgrammeDTO hrSaveInternshipProgrammeDTO) {
        if ( hrSaveInternshipProgrammeDTO == null ) {
            return null;
        }

        HRSaveInternshipProgramme.HRSaveInternshipProgrammeBuilder hRSaveInternshipProgramme = HRSaveInternshipProgramme.builder();

        hRSaveInternshipProgramme.hr( hRDTOToHR( hrSaveInternshipProgrammeDTO.getHrDTO() ) );
        hRSaveInternshipProgramme.internshipProgramme( internshipProgrammeMapper.toEntity( hrSaveInternshipProgrammeDTO.getInternshipProgrammeDTO() ) );
        hRSaveInternshipProgramme.id( hrSaveInternshipProgrammeDTO.getId() );

        return hRSaveInternshipProgramme.build();
    }

    @Override
    public HRSaveInternshipProgrammeDTO toDTO(HRSaveInternshipProgramme hrSaveInternshipProgramme) {
        if ( hrSaveInternshipProgramme == null ) {
            return null;
        }

        HRSaveInternshipProgrammeDTO.HRSaveInternshipProgrammeDTOBuilder hRSaveInternshipProgrammeDTO = HRSaveInternshipProgrammeDTO.builder();

        hRSaveInternshipProgrammeDTO.hrDTO( hRMapper.toDTO( hrSaveInternshipProgramme.getHr() ) );
        hRSaveInternshipProgrammeDTO.internshipProgrammeDTO( internshipProgrammeToInternshipProgrammeDTO( hrSaveInternshipProgramme.getInternshipProgramme() ) );
        hRSaveInternshipProgrammeDTO.id( hrSaveInternshipProgramme.getId() );

        return hRSaveInternshipProgrammeDTO.build();
    }

    protected HR hRDTOToHR(HRDTO hRDTO) {
        if ( hRDTO == null ) {
            return null;
        }

        HR hR = new HR();

        if ( hRDTO.getId() != null ) {
            hR.setId( hRDTO.getId() );
        }

        return hR;
    }

    protected InternshipProgrammeDTO internshipProgrammeToInternshipProgrammeDTO(InternshipProgramme internshipProgramme) {
        if ( internshipProgramme == null ) {
            return null;
        }

        InternshipProgrammeDTO internshipProgrammeDTO = new InternshipProgrammeDTO();

        internshipProgrammeDTO.setId( (int) internshipProgramme.getId() );
        internshipProgrammeDTO.setTitle( internshipProgramme.getTitle() );
        internshipProgrammeDTO.setRecommendation( internshipProgramme.getRecommendation() );
        internshipProgrammeDTO.setStartDate( internshipProgramme.getStartDate() );
        internshipProgrammeDTO.setEndDate( internshipProgramme.getEndDate() );
        internshipProgrammeDTO.setStudents( internshipProgramme.getStudents() );
        internshipProgrammeDTO.setCreatedDate( internshipProgramme.getCreatedDate() );
        internshipProgrammeDTO.setAmount( internshipProgramme.getAmount() );
        internshipProgrammeDTO.setLocation( internshipProgramme.getLocation() );

        return internshipProgrammeDTO;
    }
}

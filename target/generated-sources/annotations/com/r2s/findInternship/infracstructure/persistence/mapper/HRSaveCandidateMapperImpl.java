package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HrSaveCandidateDTO;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.entity.HRSaveCandidate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class HRSaveCandidateMapperImpl implements HRSaveCandidateMapper {

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public HRSaveCandidate toEntity(HrSaveCandidateDTO hrSaveCandidateDTO) {
        if ( hrSaveCandidateDTO == null ) {
            return null;
        }

        HRSaveCandidate.HRSaveCandidateBuilder hRSaveCandidate = HRSaveCandidate.builder();

        hRSaveCandidate.hr( hRDTOToHR( hrSaveCandidateDTO.getHrDTO() ) );
        hRSaveCandidate.candidate( candidateMapper.toEntity( hrSaveCandidateDTO.getCandidateDTO() ) );
        hRSaveCandidate.id( hrSaveCandidateDTO.getId() );

        return hRSaveCandidate.build();
    }

    @Override
    public HrSaveCandidateDTO toDTO(HRSaveCandidate hrSaveCandidate) {
        if ( hrSaveCandidate == null ) {
            return null;
        }

        HrSaveCandidateDTO.HrSaveCandidateDTOBuilder hrSaveCandidateDTO = HrSaveCandidateDTO.builder();

        hrSaveCandidateDTO.hrDTO( hRToHRDTO( hrSaveCandidate.getHr() ) );
        hrSaveCandidateDTO.candidateDTO( candidateMapper.toDTO( hrSaveCandidate.getCandidate() ) );
        hrSaveCandidateDTO.id( hrSaveCandidate.getId() );

        return hrSaveCandidateDTO.build();
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

    protected HRDTO hRToHRDTO(HR hR) {
        if ( hR == null ) {
            return null;
        }

        HRDTO hRDTO = new HRDTO();

        hRDTO.setId( hR.getId() );

        return hRDTO;
    }
}

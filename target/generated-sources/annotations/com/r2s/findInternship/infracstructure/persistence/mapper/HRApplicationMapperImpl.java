package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.HRApplicationDTO;
import com.r2s.findInternship.domain.entity.HRApplication;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class HRApplicationMapperImpl implements HRApplicationMapper {

    @Override
    public HRApplication toEntity(HRApplicationDTO hrApplicationDTO) {
        if ( hrApplicationDTO == null ) {
            return null;
        }

        HRApplication hRApplication = new HRApplication();

        hRApplication.setCreatedDate( hrApplicationDTO.getCreatedDate() );
        hRApplication.setLastModifiedDate( hrApplicationDTO.getLastModifiedDate() );
        hRApplication.setId( hrApplicationDTO.getId() );

        return hRApplication;
    }

    @Override
    public HRApplicationDTO toDTO(HRApplication hrApplication) {
        if ( hrApplication == null ) {
            return null;
        }

        HRApplicationDTO hRApplicationDTO = new HRApplicationDTO();

        hRApplicationDTO.setId( (int) hrApplication.getId() );
        hRApplicationDTO.setCreatedDate( hrApplication.getCreatedDate() );
        hRApplicationDTO.setLastModifiedDate( hrApplication.getLastModifiedDate() );

        return hRApplicationDTO;
    }
}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.RateDTO;
import com.r2s.findInternship.domain.entity.CompanyRate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class RateMapperImpl implements RateMapper {

    @Override
    public CompanyRate toEntity(RateDTO rateDTO) {
        if ( rateDTO == null ) {
            return null;
        }

        CompanyRate.CompanyRateBuilder companyRate = CompanyRate.builder();

        companyRate.id( rateDTO.getId() );
        companyRate.score( rateDTO.getScore() );
        companyRate.title( rateDTO.getTitle() );
        companyRate.comment( rateDTO.getComment() );

        return companyRate.build();
    }

    @Override
    public RateDTO toDTO(CompanyRate rate) {
        if ( rate == null ) {
            return null;
        }

        RateDTO rateDTO = new RateDTO();

        rateDTO.setId( (int) rate.getId() );
        rateDTO.setScore( rate.getScore() );
        rateDTO.setTitle( rate.getTitle() );
        rateDTO.setComment( rate.getComment() );
        rateDTO.setCreatedDate( rate.getCreatedDate() );

        return rateDTO;
    }
}

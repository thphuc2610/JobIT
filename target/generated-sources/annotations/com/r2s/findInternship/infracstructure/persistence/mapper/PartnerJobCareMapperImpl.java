package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerJobCareDTO;
import com.r2s.findInternship.domain.entity.Partner;
import com.r2s.findInternship.domain.entity.PartnerJobCare;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class PartnerJobCareMapperImpl implements PartnerJobCareMapper {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public PartnerJobCare toEntity(PartnerJobCareDTO partnerJobCareDTO) {
        if ( partnerJobCareDTO == null ) {
            return null;
        }

        PartnerJobCare.PartnerJobCareBuilder partnerJobCare = PartnerJobCare.builder();

        partnerJobCare.partner( partnerDTOToPartner( partnerJobCareDTO.getPartnerDTO() ) );
        partnerJobCare.job( jobMapper.jobUpdateDTOToJob( partnerJobCareDTO.getJobDTO() ) );
        partnerJobCare.id( partnerJobCareDTO.getId() );

        return partnerJobCare.build();
    }

    @Override
    public PartnerJobCareDTO toDTO(PartnerJobCare partnerJobCare) {
        if ( partnerJobCare == null ) {
            return null;
        }

        PartnerJobCareDTO.PartnerJobCareDTOBuilder partnerJobCareDTO = PartnerJobCareDTO.builder();

        partnerJobCareDTO.partnerDTO( partnerToPartnerDTO( partnerJobCare.getPartner() ) );
        partnerJobCareDTO.jobDTO( jobMapper.toDTOShow( partnerJobCare.getJob() ) );
        partnerJobCareDTO.id( partnerJobCare.getId() );

        return partnerJobCareDTO.build();
    }

    protected Partner partnerDTOToPartner(PartnerDTO partnerDTO) {
        if ( partnerDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        if ( partnerDTO.getId() != null ) {
            partner.setId( partnerDTO.getId() );
        }

        return partner;
    }

    protected PartnerDTO partnerToPartnerDTO(Partner partner) {
        if ( partner == null ) {
            return null;
        }

        PartnerDTO partnerDTO = new PartnerDTO();

        partnerDTO.setId( partner.getId() );

        return partnerDTO;
    }
}

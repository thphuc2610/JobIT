package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.application.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerOtherInfoDTO;
import com.r2s.findInternship.application.dto.partner.PartnerProfileDTO;
import com.r2s.findInternship.application.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.domain.entity.Partner;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class PartnerMapperImpl implements PartnerMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UniversityMapper universityMapper;

    @Override
    public Partner toEntity(PartnerCreationDTO partnerCreationDTO) {
        if ( partnerCreationDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setUser( userMapper.toEntity( partnerCreationDTO.getUserCreationDTO() ) );
        partner.setPosition( partnerCreationDTOPartnerOtherInfoDTOPosition( partnerCreationDTO ) );
        partner.setUniversity( universityMapper.toEntity( partnerCreationDTOPartnerOtherInfoDTOUniversityDTO( partnerCreationDTO ) ) );

        return partner;
    }

    @Override
    public Partner partnerUpdateToEntity(PartnerProfileDTO partnerProfileDTO) {
        if ( partnerProfileDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setUser( userMapper.toEntity( partnerProfileDTO.getUserProfileDTO() ) );
        partner.setPosition( partnerProfileDTOPartnerOtherInfoDTOPosition( partnerProfileDTO ) );
        partner.setUniversity( universityMapper.toEntity( partnerProfileDTOPartnerOtherInfoDTOUniversityDTO( partnerProfileDTO ) ) );

        return partner;
    }

    @Override
    public PartnerDTO toDTO(Partner partner) {
        if ( partner == null ) {
            return null;
        }

        PartnerDTO partnerDTO = new PartnerDTO();

        partnerDTO.setPartnerOtherInfoDTO( partnerToPartnerOtherInfoDTO( partner ) );
        partnerDTO.setUserDTO( userMapper.toDTO( partner.getUser() ) );
        partnerDTO.setId( partner.getId() );

        return partnerDTO;
    }

    @Override
    public Partner toEntity(PartnerDTO partnerDTO) {
        if ( partnerDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setUser( userMapper.toEntity( partnerDTO.getUserDTO() ) );
        partner.setPosition( partnerDTOPartnerOtherInfoDTOPosition( partnerDTO ) );
        partner.setUniversity( universityMapper.toEntity( partnerDTOPartnerOtherInfoDTOUniversityDTO( partnerDTO ) ) );
        if ( partnerDTO.getId() != null ) {
            partner.setId( partnerDTO.getId() );
        }

        return partner;
    }

    @Override
    public UniversityPartnerDTOShow toUniversityPartnerDTOShow(Partner partner) {
        if ( partner == null ) {
            return null;
        }

        UniversityPartnerDTOShow universityPartnerDTOShow = new UniversityPartnerDTOShow();

        universityPartnerDTOShow.setId( (int) partner.getId() );

        return universityPartnerDTOShow;
    }

    private String partnerCreationDTOPartnerOtherInfoDTOPosition(PartnerCreationDTO partnerCreationDTO) {
        if ( partnerCreationDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerCreationDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        String position = partnerOtherInfoDTO.getPosition();
        if ( position == null ) {
            return null;
        }
        return position;
    }

    private UniversityDTO partnerCreationDTOPartnerOtherInfoDTOUniversityDTO(PartnerCreationDTO partnerCreationDTO) {
        if ( partnerCreationDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerCreationDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = partnerOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }

    private String partnerProfileDTOPartnerOtherInfoDTOPosition(PartnerProfileDTO partnerProfileDTO) {
        if ( partnerProfileDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerProfileDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        String position = partnerOtherInfoDTO.getPosition();
        if ( position == null ) {
            return null;
        }
        return position;
    }

    private UniversityDTO partnerProfileDTOPartnerOtherInfoDTOUniversityDTO(PartnerProfileDTO partnerProfileDTO) {
        if ( partnerProfileDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerProfileDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = partnerOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }

    protected PartnerOtherInfoDTO partnerToPartnerOtherInfoDTO(Partner partner) {
        if ( partner == null ) {
            return null;
        }

        PartnerOtherInfoDTO partnerOtherInfoDTO = new PartnerOtherInfoDTO();

        partnerOtherInfoDTO.setPosition( partner.getPosition() );
        partnerOtherInfoDTO.setUniversityDTO( universityMapper.toDTO( partner.getUniversity() ) );

        return partnerOtherInfoDTO;
    }

    private String partnerDTOPartnerOtherInfoDTOPosition(PartnerDTO partnerDTO) {
        if ( partnerDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        String position = partnerOtherInfoDTO.getPosition();
        if ( position == null ) {
            return null;
        }
        return position;
    }

    private UniversityDTO partnerDTOPartnerOtherInfoDTOUniversityDTO(PartnerDTO partnerDTO) {
        if ( partnerDTO == null ) {
            return null;
        }
        PartnerOtherInfoDTO partnerOtherInfoDTO = partnerDTO.getPartnerOtherInfoDTO();
        if ( partnerOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = partnerOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }
}

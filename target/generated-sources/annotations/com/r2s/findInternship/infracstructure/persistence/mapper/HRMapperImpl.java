package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.CompanyDTO;
import com.r2s.findInternship.application.dto.hr.HRCreationDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HROtherInfoDTO;
import com.r2s.findInternship.application.dto.hr.HRProfileDTO;
import com.r2s.findInternship.domain.entity.HR;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class HRMapperImpl implements HRMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public HR toEntity(HRCreationDTO hrCreationDTO) {
        if ( hrCreationDTO == null ) {
            return null;
        }

        HR hR = new HR();

        hR.setUser( userMapper.toEntity( hrCreationDTO.getUserCreationDTO() ) );
        hR.setPosition( hrCreationDTOHrOtherInfoDTOPosition( hrCreationDTO ) );
        hR.setCompany( companyMapper.toEntity( hrCreationDTOHrOtherInfoDTOCompanyDTO( hrCreationDTO ) ) );

        return hR;
    }

    @Override
    public HR toEntity(HRProfileDTO hrProfileDTO) {
        if ( hrProfileDTO == null ) {
            return null;
        }

        HR hR = new HR();

        hR.setUser( userMapper.toEntity( hrProfileDTO.getUserProfileDTO() ) );
        hR.setPosition( hrProfileDTO.getPosition() );

        return hR;
    }

    @Override
    public HRDTO toDTO(HR hr) {
        if ( hr == null ) {
            return null;
        }

        HRDTO hRDTO = new HRDTO();

        hRDTO.setHrOtherInfoDTO( hRToHROtherInfoDTO( hr ) );
        hRDTO.setUserDTO( userMapper.toDTO( hr.getUser() ) );
        hRDTO.setId( hr.getId() );

        return hRDTO;
    }

    private String hrCreationDTOHrOtherInfoDTOPosition(HRCreationDTO hRCreationDTO) {
        if ( hRCreationDTO == null ) {
            return null;
        }
        HROtherInfoDTO hrOtherInfoDTO = hRCreationDTO.getHrOtherInfoDTO();
        if ( hrOtherInfoDTO == null ) {
            return null;
        }
        String position = hrOtherInfoDTO.getPosition();
        if ( position == null ) {
            return null;
        }
        return position;
    }

    private CompanyDTO hrCreationDTOHrOtherInfoDTOCompanyDTO(HRCreationDTO hRCreationDTO) {
        if ( hRCreationDTO == null ) {
            return null;
        }
        HROtherInfoDTO hrOtherInfoDTO = hRCreationDTO.getHrOtherInfoDTO();
        if ( hrOtherInfoDTO == null ) {
            return null;
        }
        CompanyDTO companyDTO = hrOtherInfoDTO.getCompanyDTO();
        if ( companyDTO == null ) {
            return null;
        }
        return companyDTO;
    }

    protected HROtherInfoDTO hRToHROtherInfoDTO(HR hR) {
        if ( hR == null ) {
            return null;
        }

        HROtherInfoDTO hROtherInfoDTO = new HROtherInfoDTO();

        hROtherInfoDTO.setPosition( hR.getPosition() );
        hROtherInfoDTO.setCompanyDTO( companyMapper.toDTO( hR.getCompany() ) );

        return hROtherInfoDTO;
    }
}

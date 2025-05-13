package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerOtherInfoDTO;
import com.r2s.findInternship.application.dto.partner.PartnerProfileDTO;
import com.r2s.findInternship.application.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.domain.entity.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UniversityMapper.class})
public interface PartnerMapper {

    @Mapping(source = "userCreationDTO", target = "user")
    @Mapping(source = "partnerOtherInfoDTO.position", target = "position")
    @Mapping(source = "partnerOtherInfoDTO.universityDTO", target = "university")
    Partner toEntity(PartnerCreationDTO partnerCreationDTO);

    @Mapping(source = "userProfileDTO", target = "user")
    @Mapping(source = "partnerOtherInfoDTO.position", target = "position")
    @Mapping(source = "partnerOtherInfoDTO.universityDTO", target = "university")
    Partner partnerUpdateToEntity(PartnerProfileDTO partnerProfileDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "position", target = "partnerOtherInfoDTO.position")
    @Mapping(source = "university", target = "partnerOtherInfoDTO.universityDTO")
    PartnerDTO toDTO(Partner partner);

    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "partnerOtherInfoDTO.position",  target = "position")
    @Mapping(source = "partnerOtherInfoDTO.universityDTO",  target = "university")
    Partner toEntity(PartnerDTO partnerDTO);

    UniversityPartnerDTOShow toUniversityPartnerDTOShow(Partner partner);
}

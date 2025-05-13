package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.partner.PartnerJobCareDTO;
import com.r2s.findInternship.domain.entity.PartnerJobCare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = JobMapper.class)
public interface PartnerJobCareMapper {
    @Mapping(source = "partnerDTO", target = "partner")
    @Mapping(source = "jobDTO", target = "job")
    PartnerJobCare toEntity(PartnerJobCareDTO partnerJobCareDTO);

    @Mapping(source = "partner", target = "partnerDTO")
    @Mapping(source = "job", target = "jobDTO")
    PartnerJobCareDTO toDTO(PartnerJobCare partnerJobCare);
}
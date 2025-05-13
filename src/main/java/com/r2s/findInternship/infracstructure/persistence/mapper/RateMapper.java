package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.RateDTO;
import com.r2s.findInternship.domain.entity.CompanyRate;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RateMapper {
    CompanyRate toEntity(RateDTO rateDTO);

    RateDTO toDTO(CompanyRate rate);

    // @Mapping(source = "rateDTO.title", target = "title")
    // @Mapping(source = "rateDTO.hide", target = "hide")
    // @Mapping(source = "rateDTO.createdDate", target = "createdDate")
    // @Mapping(source = "rateDTO.score", target = "score")
    // @Mapping(source = "rateDTO.comment", target = "comment")
    // @Mapping(source = "oldEntity.id", target = "id")
    // @Mapping(source = "oldEntity.status", target = "status")
    // @Mapping(source = "oldEntity.company", target = "company")
    // CompanyRate toEntityUpdate(CompanyRate oldEntity, RateDTO rateDTO);
}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.CompanyDTO;
import com.r2s.findInternship.domain.entity.Company;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StatusMapper.class})
public interface CompanyMapper {
    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "province", target = "province")
    @Mapping(source = "district", target = "district")
    Company toEntity(CompanyDTO companyDTO);

    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "province", target = "province")
    @Mapping(source = "district", target = "district")
    CompanyDTO toDTO(Company company);
}
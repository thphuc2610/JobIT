package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.CompanyDTO;
import com.r2s.findInternship.domain.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.status( statusMapper.toEntity( companyDTO.getStatusDTO() ) );
        company.country( companyDTO.getCountry() );
        company.province( companyDTO.getProvince() );
        company.district( companyDTO.getDistrict() );
        if ( companyDTO.getId() != null ) {
            company.id( companyDTO.getId() );
        }
        company.name( companyDTO.getName() );
        company.logo( companyDTO.getLogo() );
        company.description( companyDTO.getDescription() );
        company.website( companyDTO.getWebsite() );
        company.email( companyDTO.getEmail() );
        company.phone( companyDTO.getPhone() );
        company.tax( companyDTO.getTax() );
        company.location( companyDTO.getLocation() );
        company.personnelSize( companyDTO.getPersonnelSize() );

        return company.build();
    }

    @Override
    public CompanyDTO toDTO(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTO.CompanyDTOBuilder companyDTO = CompanyDTO.builder();

        companyDTO.statusDTO( statusMapper.toDTO( company.getStatus() ) );
        companyDTO.country( company.getCountry() );
        companyDTO.province( company.getProvince() );
        companyDTO.district( company.getDistrict() );
        companyDTO.id( company.getId() );
        companyDTO.logo( company.getLogo() );
        companyDTO.name( company.getName() );
        companyDTO.tax( company.getTax() );
        companyDTO.email( company.getEmail() );
        companyDTO.phone( company.getPhone() );
        companyDTO.personnelSize( company.getPersonnelSize() );
        companyDTO.website( company.getWebsite() );
        companyDTO.createdDate( company.getCreatedDate() );
        companyDTO.location( company.getLocation() );
        companyDTO.description( company.getDescription() );

        return companyDTO.build();
    }
}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.UniversityTypeDTO;
import com.r2s.findInternship.domain.entity.UniversityType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UniversityTypeMapperImpl implements UniversityTypeMapper {

    @Override
    public UniversityType toEntity(UniversityTypeDTO universityTypeDTO) {
        if ( universityTypeDTO == null ) {
            return null;
        }

        UniversityType universityType = new UniversityType();

        universityType.setId( universityTypeDTO.getId() );
        universityType.setName( universityTypeDTO.getName() );

        return universityType;
    }

    @Override
    public UniversityTypeDTO toDTO(UniversityType universityType) {
        if ( universityType == null ) {
            return null;
        }

        UniversityTypeDTO.UniversityTypeDTOBuilder universityTypeDTO = UniversityTypeDTO.builder();

        universityTypeDTO.id( universityType.getId() );
        universityTypeDTO.name( universityType.getName() );

        return universityTypeDTO.build();
    }
}

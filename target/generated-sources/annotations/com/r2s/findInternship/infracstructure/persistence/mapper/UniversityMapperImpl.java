package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.domain.entity.University;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UniversityMapperImpl implements UniversityMapper {

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private UniversityTypeMapper universityTypeMapper;

    @Override
    public University toEntity(UniversityDTO universityDTO) {
        if ( universityDTO == null ) {
            return null;
        }

        University university = new University();

        university.setUniversityType( universityTypeMapper.toEntity( universityDTO.getUniversityTypeDTO() ) );
        university.setStatus( statusMapper.toEntity( universityDTO.getStatusDTO() ) );
        university.setCreatedDate( universityDTO.getCreatedDate() );
        if ( universityDTO.getId() != null ) {
            university.setId( universityDTO.getId() );
        }
        university.setName( universityDTO.getName() );
        university.setShortName( universityDTO.getShortName() );
        university.setAvatar( universityDTO.getAvatar() );
        university.setEmail( universityDTO.getEmail() );
        university.setPhone( universityDTO.getPhone() );
        university.setWebsite( universityDTO.getWebsite() );
        university.setDescription( universityDTO.getDescription() );
        university.setLocation( universityDTO.getLocation() );

        return university;
    }

    @Override
    public UniversityDTO toDTO(University university) {
        if ( university == null ) {
            return null;
        }

        UniversityDTO.UniversityDTOBuilder universityDTO = UniversityDTO.builder();

        universityDTO.universityTypeDTO( universityTypeMapper.toDTO( university.getUniversityType() ) );
        universityDTO.statusDTO( statusMapper.toDTO( university.getStatus() ) );
        universityDTO.id( university.getId() );
        universityDTO.name( university.getName() );
        universityDTO.avatar( university.getAvatar() );
        universityDTO.shortName( university.getShortName() );
        universityDTO.description( university.getDescription() );
        universityDTO.website( university.getWebsite() );
        universityDTO.email( university.getEmail() );
        universityDTO.phone( university.getPhone() );
        universityDTO.location( university.getLocation() );
        universityDTO.createdDate( university.getCreatedDate() );

        return universityDTO.build();
    }
}

package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.StatusDTO;
import com.r2s.findInternship.domain.entity.Status;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public Status toEntity(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status.StatusBuilder status = Status.builder();

        status.id( statusDTO.getId() );
        status.name( statusDTO.getName() );

        return status.build();
    }

    @Override
    public StatusDTO toDTO(Status status) {
        if ( status == null ) {
            return null;
        }

        StatusDTO.StatusDTOBuilder statusDTO = StatusDTO.builder();

        statusDTO.id( status.getId() );
        statusDTO.name( status.getName() );

        return statusDTO.build();
    }
}

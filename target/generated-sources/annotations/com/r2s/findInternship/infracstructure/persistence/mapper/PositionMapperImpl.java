package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.CandidatePosition;
import com.r2s.findInternship.domain.entity.InternshipPosition;
import com.r2s.findInternship.domain.entity.JobPosition;
import com.r2s.findInternship.domain.entity.Position;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toEntity(PositionDTO positionDTO) {
        if ( positionDTO == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.id( positionDTO.getId() );
        position.name( positionDTO.getName() );

        return position.build();
    }

    @Override
    public PositionDTO toDTO(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionDTO.PositionDTOBuilder positionDTO = PositionDTO.builder();

        positionDTO.id( position.getId() );
        positionDTO.name( position.getName() );

        return positionDTO.build();
    }

    @Override
    public PositionDTO toPositionDTO(CandidatePosition candidatePosition) {
        if ( candidatePosition == null ) {
            return null;
        }

        PositionDTO.PositionDTOBuilder positionDTO = PositionDTO.builder();

        positionDTO.id( candidatePositionPositionId( candidatePosition ) );
        positionDTO.name( candidatePositionPositionName( candidatePosition ) );

        return positionDTO.build();
    }

    @Override
    public PositionDTO jobPositiontoPositionDto(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }

        PositionDTO.PositionDTOBuilder positionDTO = PositionDTO.builder();

        positionDTO.id( jobPositionPositionId( jobPosition ) );
        positionDTO.name( jobPositionPositionName( jobPosition ) );

        return positionDTO.build();
    }

    @Override
    public PositionDTO toPositionDto(InternshipPosition internshipPosition) {
        if ( internshipPosition == null ) {
            return null;
        }

        PositionDTO.PositionDTOBuilder positionDTO = PositionDTO.builder();

        positionDTO.id( internshipPositionPositionId( internshipPosition ) );
        positionDTO.name( internshipPositionPositionName( internshipPosition ) );

        return positionDTO.build();
    }

    private int candidatePositionPositionId(CandidatePosition candidatePosition) {
        if ( candidatePosition == null ) {
            return 0;
        }
        Position position = candidatePosition.getPosition();
        if ( position == null ) {
            return 0;
        }
        int id = position.getId();
        return id;
    }

    private String candidatePositionPositionName(CandidatePosition candidatePosition) {
        if ( candidatePosition == null ) {
            return null;
        }
        Position position = candidatePosition.getPosition();
        if ( position == null ) {
            return null;
        }
        String name = position.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int jobPositionPositionId(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return 0;
        }
        Position position = jobPosition.getPosition();
        if ( position == null ) {
            return 0;
        }
        int id = position.getId();
        return id;
    }

    private String jobPositionPositionName(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Position position = jobPosition.getPosition();
        if ( position == null ) {
            return null;
        }
        String name = position.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int internshipPositionPositionId(InternshipPosition internshipPosition) {
        if ( internshipPosition == null ) {
            return 0;
        }
        Position position = internshipPosition.getPosition();
        if ( position == null ) {
            return 0;
        }
        int id = position.getId();
        return id;
    }

    private String internshipPositionPositionName(InternshipPosition internshipPosition) {
        if ( internshipPosition == null ) {
            return null;
        }
        Position position = internshipPosition.getPosition();
        if ( position == null ) {
            return null;
        }
        String name = position.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

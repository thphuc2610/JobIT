package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.application.dto.InternshipScheduleDTO;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.InternshipSchedule;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class InternshipScheduleMapperImpl implements InternshipScheduleMapper {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public InternshipSchedule toEntity(InternshipScheduleDTO jobTypeDemandDTO) {
        if ( jobTypeDemandDTO == null ) {
            return null;
        }

        InternshipSchedule internshipSchedule = new InternshipSchedule();

        internshipSchedule.setId( jobTypeDemandDTO.getId() );

        return internshipSchedule;
    }

    @Override
    public InternshipScheduleDTO toDTO(InternshipSchedule jobTypeDemand) {
        if ( jobTypeDemand == null ) {
            return null;
        }

        InternshipScheduleDTO internshipScheduleDTO = new InternshipScheduleDTO();

        internshipScheduleDTO.setInternshipProgrammeDTO( internshipProgrammeToInternshipProgrammeDTO( jobTypeDemand.getInternshipProgramme() ) );
        internshipScheduleDTO.setScheduleDTO( scheduleMapper.toDTO( jobTypeDemand.getSchedule() ) );
        internshipScheduleDTO.setId( jobTypeDemand.getId() );

        return internshipScheduleDTO;
    }

    protected InternshipProgrammeDTO internshipProgrammeToInternshipProgrammeDTO(InternshipProgramme internshipProgramme) {
        if ( internshipProgramme == null ) {
            return null;
        }

        InternshipProgrammeDTO internshipProgrammeDTO = new InternshipProgrammeDTO();

        internshipProgrammeDTO.setId( (int) internshipProgramme.getId() );
        internshipProgrammeDTO.setTitle( internshipProgramme.getTitle() );
        internshipProgrammeDTO.setRecommendation( internshipProgramme.getRecommendation() );
        internshipProgrammeDTO.setStartDate( internshipProgramme.getStartDate() );
        internshipProgrammeDTO.setEndDate( internshipProgramme.getEndDate() );
        internshipProgrammeDTO.setStudents( internshipProgramme.getStudents() );
        internshipProgrammeDTO.setCreatedDate( internshipProgramme.getCreatedDate() );
        internshipProgrammeDTO.setAmount( internshipProgramme.getAmount() );
        internshipProgrammeDTO.setLocation( internshipProgramme.getLocation() );

        return internshipProgrammeDTO;
    }
}

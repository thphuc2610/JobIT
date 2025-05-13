package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.CandidateSchedule;
import com.r2s.findInternship.domain.entity.InternshipSchedule;
import com.r2s.findInternship.domain.entity.JobSchedule;
import com.r2s.findInternship.domain.entity.Schedule;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( scheduleDTO.getId() );
        schedule.setName( scheduleDTO.getName() );

        return schedule;
    }

    @Override
    public ScheduleDTO toDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( schedule.getId() );
        scheduleDTO.name( schedule.getName() );

        return scheduleDTO.build();
    }

    @Override
    public ScheduleDTO toScheduleDTO(CandidateSchedule candidateSchedule) {
        if ( candidateSchedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( candidateScheduleScheduleId( candidateSchedule ) );
        scheduleDTO.name( candidateScheduleScheduleName( candidateSchedule ) );

        return scheduleDTO.build();
    }

    @Override
    public ScheduleDTO toDTO(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( jobScheduleScheduleId( jobSchedule ) );
        scheduleDTO.name( jobScheduleScheduleName( jobSchedule ) );

        return scheduleDTO.build();
    }

    @Override
    public ScheduleDTO toDTO(InternshipSchedule internshipSchedule) {
        if ( internshipSchedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( internshipScheduleScheduleId( internshipSchedule ) );
        scheduleDTO.name( internshipScheduleScheduleName( internshipSchedule ) );

        return scheduleDTO.build();
    }

    private int candidateScheduleScheduleId(CandidateSchedule candidateSchedule) {
        if ( candidateSchedule == null ) {
            return 0;
        }
        Schedule schedule = candidateSchedule.getSchedule();
        if ( schedule == null ) {
            return 0;
        }
        int id = schedule.getId();
        return id;
    }

    private String candidateScheduleScheduleName(CandidateSchedule candidateSchedule) {
        if ( candidateSchedule == null ) {
            return null;
        }
        Schedule schedule = candidateSchedule.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        String name = schedule.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int jobScheduleScheduleId(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return 0;
        }
        Schedule schedule = jobSchedule.getSchedule();
        if ( schedule == null ) {
            return 0;
        }
        int id = schedule.getId();
        return id;
    }

    private String jobScheduleScheduleName(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Schedule schedule = jobSchedule.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        String name = schedule.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int internshipScheduleScheduleId(InternshipSchedule internshipSchedule) {
        if ( internshipSchedule == null ) {
            return 0;
        }
        Schedule schedule = internshipSchedule.getSchedule();
        if ( schedule == null ) {
            return 0;
        }
        int id = schedule.getId();
        return id;
    }

    private String internshipScheduleScheduleName(InternshipSchedule internshipSchedule) {
        if ( internshipSchedule == null ) {
            return null;
        }
        Schedule schedule = internshipSchedule.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        String name = schedule.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

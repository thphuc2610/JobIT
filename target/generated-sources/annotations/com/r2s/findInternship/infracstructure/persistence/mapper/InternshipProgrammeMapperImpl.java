package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;
import com.r2s.findInternship.domain.entity.InternshipMajor;
import com.r2s.findInternship.domain.entity.InternshipPosition;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.InternshipSchedule;
import com.r2s.findInternship.domain.entity.University;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class InternshipProgrammeMapperImpl implements InternshipProgrammeMapper {

    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public InternshipProgramme toEntity(InternshipProgrammeDTO internshipProgrammeDTO) {
        if ( internshipProgrammeDTO == null ) {
            return null;
        }

        InternshipProgramme internshipProgramme = new InternshipProgramme();

        internshipProgramme.setTitle( internshipProgrammeDTO.getTitle() );
        internshipProgramme.setRecommendation( internshipProgrammeDTO.getRecommendation() );
        internshipProgramme.setStartDate( internshipProgrammeDTO.getStartDate() );
        internshipProgramme.setEndDate( internshipProgrammeDTO.getEndDate() );
        internshipProgramme.setStudents( internshipProgrammeDTO.getStudents() );
        internshipProgramme.setAmount( internshipProgrammeDTO.getAmount() );
        internshipProgramme.setLocation( internshipProgrammeDTO.getLocation() );
        internshipProgramme.setUniversity( universityDTOToUniversity( internshipProgrammeDTO.getUniversityDTO() ) );
        internshipProgramme.setStatus( statusMapper.toEntity( internshipProgrammeDTO.getStatusDTO() ) );
        internshipProgramme.setInternshipPositions( positionDTOListToInternshipPositionList( internshipProgrammeDTO.getPositionDTOs() ) );
        internshipProgramme.setInternshipSchedules( scheduleDTOListToInternshipScheduleList( internshipProgrammeDTO.getScheduleDTOs() ) );
        internshipProgramme.setInternshipMajors( majorDTOListToInternshipMajorList( internshipProgrammeDTO.getMajorDTOs() ) );
        internshipProgramme.setCreatedDate( internshipProgrammeDTO.getCreatedDate() );
        internshipProgramme.setId( internshipProgrammeDTO.getId() );

        return internshipProgramme;
    }

    @Override
    public InternshipProgrammeDTOShow toDTOShow(InternshipProgramme internshipProgramme) {
        if ( internshipProgramme == null ) {
            return null;
        }

        InternshipProgrammeDTOShow internshipProgrammeDTOShow = new InternshipProgrammeDTOShow();

        internshipProgrammeDTOShow.setId( (int) internshipProgramme.getId() );
        internshipProgrammeDTOShow.setTitle( internshipProgramme.getTitle() );
        internshipProgrammeDTOShow.setRecommendation( internshipProgramme.getRecommendation() );
        internshipProgrammeDTOShow.setStudents( internshipProgramme.getStudents() );
        internshipProgrammeDTOShow.setAmount( internshipProgramme.getAmount() );
        internshipProgrammeDTOShow.setLocation( internshipProgramme.getLocation() );
        internshipProgrammeDTOShow.setStartDate( internshipProgramme.getStartDate() );
        internshipProgrammeDTOShow.setEndDate( internshipProgramme.getEndDate() );
        internshipProgrammeDTOShow.setUniversityDTO( universityToUniversityDTO( internshipProgramme.getUniversity() ) );
        internshipProgrammeDTOShow.setStatusDTO( statusMapper.toDTO( internshipProgramme.getStatus() ) );
        internshipProgrammeDTOShow.setPositionDTOs( internshipPositionListToPositionDTOList( internshipProgramme.getInternshipPositions() ) );
        internshipProgrammeDTOShow.setScheduleDTOs( internshipScheduleListToScheduleDTOList( internshipProgramme.getInternshipSchedules() ) );
        internshipProgrammeDTOShow.setMajorDTOs( internshipMajorListToMajorDTOList( internshipProgramme.getInternshipMajors() ) );
        internshipProgrammeDTOShow.setCreatedDate( internshipProgramme.getCreatedDate() );
        internshipProgrammeDTOShow.setLastModifiedDate( internshipProgramme.getLastModifiedDate() );

        return internshipProgrammeDTOShow;
    }

    @Override
    public void updateEntityFromDTO(InternshipProgrammeDTO universityDemandDTO, InternshipProgramme existingEntity) {
        if ( universityDemandDTO == null ) {
            return;
        }

        existingEntity.setTitle( universityDemandDTO.getTitle() );
        existingEntity.setAmount( universityDemandDTO.getAmount() );
        existingEntity.setLocation( universityDemandDTO.getLocation() );
        existingEntity.setEndDate( universityDemandDTO.getEndDate() );
        existingEntity.setStartDate( universityDemandDTO.getStartDate() );
        if ( existingEntity.getInternshipPositions() != null ) {
            List<InternshipPosition> list = positionDTOListToInternshipPositionList( universityDemandDTO.getPositionDTOs() );
            if ( list != null ) {
                existingEntity.getInternshipPositions().clear();
                existingEntity.getInternshipPositions().addAll( list );
            }
            else {
                existingEntity.setInternshipPositions( null );
            }
        }
        else {
            List<InternshipPosition> list = positionDTOListToInternshipPositionList( universityDemandDTO.getPositionDTOs() );
            if ( list != null ) {
                existingEntity.setInternshipPositions( list );
            }
        }
        if ( existingEntity.getInternshipMajors() != null ) {
            List<InternshipMajor> list1 = majorDTOListToInternshipMajorList( universityDemandDTO.getMajorDTOs() );
            if ( list1 != null ) {
                existingEntity.getInternshipMajors().clear();
                existingEntity.getInternshipMajors().addAll( list1 );
            }
            else {
                existingEntity.setInternshipMajors( null );
            }
        }
        else {
            List<InternshipMajor> list1 = majorDTOListToInternshipMajorList( universityDemandDTO.getMajorDTOs() );
            if ( list1 != null ) {
                existingEntity.setInternshipMajors( list1 );
            }
        }
        if ( existingEntity.getInternshipSchedules() != null ) {
            List<InternshipSchedule> list2 = scheduleDTOListToInternshipScheduleList( universityDemandDTO.getScheduleDTOs() );
            if ( list2 != null ) {
                existingEntity.getInternshipSchedules().clear();
                existingEntity.getInternshipSchedules().addAll( list2 );
            }
            else {
                existingEntity.setInternshipSchedules( null );
            }
        }
        else {
            List<InternshipSchedule> list2 = scheduleDTOListToInternshipScheduleList( universityDemandDTO.getScheduleDTOs() );
            if ( list2 != null ) {
                existingEntity.setInternshipSchedules( list2 );
            }
        }
        existingEntity.setStudents( universityDemandDTO.getStudents() );
        existingEntity.setRecommendation( universityDemandDTO.getRecommendation() );
        existingEntity.setCreatedDate( universityDemandDTO.getCreatedDate() );
    }

    protected University universityDTOToUniversity(UniversityDTO universityDTO) {
        if ( universityDTO == null ) {
            return null;
        }

        University university = new University();

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

    protected InternshipPosition positionDTOToInternshipPosition(PositionDTO positionDTO) {
        if ( positionDTO == null ) {
            return null;
        }

        InternshipPosition internshipPosition = new InternshipPosition();

        internshipPosition.setId( positionDTO.getId() );

        return internshipPosition;
    }

    protected List<InternshipPosition> positionDTOListToInternshipPositionList(List<PositionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InternshipPosition> list1 = new ArrayList<InternshipPosition>( list.size() );
        for ( PositionDTO positionDTO : list ) {
            list1.add( positionDTOToInternshipPosition( positionDTO ) );
        }

        return list1;
    }

    protected InternshipSchedule scheduleDTOToInternshipSchedule(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        InternshipSchedule internshipSchedule = new InternshipSchedule();

        internshipSchedule.setId( (long) scheduleDTO.getId() );

        return internshipSchedule;
    }

    protected List<InternshipSchedule> scheduleDTOListToInternshipScheduleList(List<ScheduleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InternshipSchedule> list1 = new ArrayList<InternshipSchedule>( list.size() );
        for ( ScheduleDTO scheduleDTO : list ) {
            list1.add( scheduleDTOToInternshipSchedule( scheduleDTO ) );
        }

        return list1;
    }

    protected InternshipMajor majorDTOToInternshipMajor(MajorDTO majorDTO) {
        if ( majorDTO == null ) {
            return null;
        }

        InternshipMajor internshipMajor = new InternshipMajor();

        internshipMajor.setId( majorDTO.getId() );

        return internshipMajor;
    }

    protected List<InternshipMajor> majorDTOListToInternshipMajorList(List<MajorDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InternshipMajor> list1 = new ArrayList<InternshipMajor>( list.size() );
        for ( MajorDTO majorDTO : list ) {
            list1.add( majorDTOToInternshipMajor( majorDTO ) );
        }

        return list1;
    }

    protected UniversityDTO universityToUniversityDTO(University university) {
        if ( university == null ) {
            return null;
        }

        UniversityDTO.UniversityDTOBuilder universityDTO = UniversityDTO.builder();

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

    protected List<PositionDTO> internshipPositionListToPositionDTOList(List<InternshipPosition> list) {
        if ( list == null ) {
            return null;
        }

        List<PositionDTO> list1 = new ArrayList<PositionDTO>( list.size() );
        for ( InternshipPosition internshipPosition : list ) {
            list1.add( positionMapper.toPositionDto( internshipPosition ) );
        }

        return list1;
    }

    protected List<ScheduleDTO> internshipScheduleListToScheduleDTOList(List<InternshipSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<ScheduleDTO> list1 = new ArrayList<ScheduleDTO>( list.size() );
        for ( InternshipSchedule internshipSchedule : list ) {
            list1.add( scheduleMapper.toDTO( internshipSchedule ) );
        }

        return list1;
    }

    protected List<MajorDTO> internshipMajorListToMajorDTOList(List<InternshipMajor> list) {
        if ( list == null ) {
            return null;
        }

        List<MajorDTO> list1 = new ArrayList<MajorDTO>( list.size() );
        for ( InternshipMajor internshipMajor : list ) {
            list1.add( majorMapper.toMajorDto( internshipMajor ) );
        }

        return list1;
    }
}

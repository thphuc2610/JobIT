package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobCreationDTO;
import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Company;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobMajor;
import com.r2s.findInternship.domain.entity.JobPosition;
import com.r2s.findInternship.domain.entity.JobSchedule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Job toEntity(JobCreationDTO jobCreationDTO) {
        if ( jobCreationDTO == null ) {
            return null;
        }

        Job job = new Job();

        job.setJobPositions( positionDTOListToJobPositionList( jobCreationDTO.getPositionDTOS() ) );
        job.setJobSchedules( scheduleDTOListToJobScheduleList( jobCreationDTO.getScheduleDTOS() ) );
        job.setJobMajors( majorDTOListToJobMajorList( jobCreationDTO.getMajorDTOS() ) );
        job.setTitle( jobCreationDTO.getTitle() );
        job.setAmount( jobCreationDTO.getAmount() );
        job.setPostingDate( jobCreationDTO.getPostingDate() );
        job.setApplicationDeadline( jobCreationDTO.getApplicationDeadline() );
        job.setMinAllowance( jobCreationDTO.getMinAllowance() );
        job.setMaxAllowance( jobCreationDTO.getMaxAllowance() );
        if ( jobCreationDTO.getNoAllowance() != null ) {
            job.setNoAllowance( jobCreationDTO.getNoAllowance() );
        }
        job.setCountry( jobCreationDTO.getCountry() );
        job.setCity( jobCreationDTO.getCity() );
        job.setDistrict( jobCreationDTO.getDistrict() );
        job.setAddress( jobCreationDTO.getAddress() );
        job.setDescription( jobCreationDTO.getDescription() );
        job.setRequirements( jobCreationDTO.getRequirements() );
        job.setBenefits( jobCreationDTO.getBenefits() );

        return job;
    }

    @Override
    public Job jobUpdateDTOToJob(JobDTO jobDTO) {
        if ( jobDTO == null ) {
            return null;
        }

        Job job = new Job();

        job.setJobPositions( positionDTOListToJobPositionList( jobDTO.getPositionDTOS() ) );
        job.setJobSchedules( scheduleDTOListToJobScheduleList( jobDTO.getScheduleDTOS() ) );
        job.setJobMajors( majorDTOListToJobMajorList( jobDTO.getMajorDTOS() ) );
        job.setStatus( statusMapper.toEntity( jobDTO.getStatusDTO() ) );
        if ( jobDTO.getId() != null ) {
            job.setId( jobDTO.getId() );
        }
        job.setTitle( jobDTO.getTitle() );
        job.setAmount( jobDTO.getAmount() );
        job.setPostingDate( jobDTO.getPostingDate() );
        job.setApplicationDeadline( jobDTO.getApplicationDeadline() );
        job.setMinAllowance( jobDTO.getMinAllowance() );
        job.setMaxAllowance( jobDTO.getMaxAllowance() );
        if ( jobDTO.getNoAllowance() != null ) {
            job.setNoAllowance( jobDTO.getNoAllowance() );
        }
        job.setCountry( jobDTO.getCountry() );
        job.setCity( jobDTO.getCity() );
        job.setDistrict( jobDTO.getDistrict() );
        job.setAddress( jobDTO.getAddress() );
        job.setDescription( jobDTO.getDescription() );
        job.setRequirements( jobDTO.getRequirements() );
        job.setBenefits( jobDTO.getBenefits() );

        return job;
    }

    @Override
    public JobDTO toDTOShow(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

        jobDTO.setPositionDTOS( jobPositionListToPositionDTOList( job.getJobPositions() ) );
        jobDTO.setScheduleDTOS( jobScheduleListToScheduleDTOList( job.getJobSchedules() ) );
        jobDTO.setMajorDTOS( majorMapper.jobMajortoMajorDto( job.getJobMajors() ) );
        jobDTO.setStatusDTO( statusMapper.toDTO( job.getStatus() ) );
        jobDTO.setCompanyDTO( companyMapper.toDTO( jobHrCompany( job ) ) );
        jobDTO.setTitle( job.getTitle() );
        jobDTO.setAmount( job.getAmount() );
        jobDTO.setPostingDate( job.getPostingDate() );
        jobDTO.setApplicationDeadline( job.getApplicationDeadline() );
        jobDTO.setMinAllowance( job.getMinAllowance() );
        jobDTO.setMaxAllowance( job.getMaxAllowance() );
        jobDTO.setNoAllowance( job.isNoAllowance() );
        jobDTO.setDescription( job.getDescription() );
        jobDTO.setRequirements( job.getRequirements() );
        jobDTO.setBenefits( job.getBenefits() );
        jobDTO.setCountry( job.getCountry() );
        jobDTO.setCity( job.getCity() );
        jobDTO.setDistrict( job.getDistrict() );
        jobDTO.setAddress( job.getAddress() );
        jobDTO.setId( job.getId() );

        return jobDTO;
    }

    @Override
    public void updateJobFromJobDTO(JobDTO jobDTO, Job existingEntity) {
        if ( jobDTO == null ) {
            return;
        }

        if ( existingEntity.getJobPositions() != null ) {
            List<JobPosition> list = positionDTOListToJobPositionList( jobDTO.getPositionDTOS() );
            if ( list != null ) {
                existingEntity.getJobPositions().clear();
                existingEntity.getJobPositions().addAll( list );
            }
            else {
                existingEntity.setJobPositions( null );
            }
        }
        else {
            List<JobPosition> list = positionDTOListToJobPositionList( jobDTO.getPositionDTOS() );
            if ( list != null ) {
                existingEntity.setJobPositions( list );
            }
        }
        if ( existingEntity.getJobSchedules() != null ) {
            List<JobSchedule> list1 = scheduleDTOListToJobScheduleList( jobDTO.getScheduleDTOS() );
            if ( list1 != null ) {
                existingEntity.getJobSchedules().clear();
                existingEntity.getJobSchedules().addAll( list1 );
            }
            else {
                existingEntity.setJobSchedules( null );
            }
        }
        else {
            List<JobSchedule> list1 = scheduleDTOListToJobScheduleList( jobDTO.getScheduleDTOS() );
            if ( list1 != null ) {
                existingEntity.setJobSchedules( list1 );
            }
        }
        if ( existingEntity.getJobMajors() != null ) {
            List<JobMajor> list2 = majorDTOListToJobMajorList( jobDTO.getMajorDTOS() );
            if ( list2 != null ) {
                existingEntity.getJobMajors().clear();
                existingEntity.getJobMajors().addAll( list2 );
            }
            else {
                existingEntity.setJobMajors( null );
            }
        }
        else {
            List<JobMajor> list2 = majorDTOListToJobMajorList( jobDTO.getMajorDTOS() );
            if ( list2 != null ) {
                existingEntity.setJobMajors( list2 );
            }
        }
        existingEntity.setTitle( jobDTO.getTitle() );
        existingEntity.setAmount( jobDTO.getAmount() );
        existingEntity.setPostingDate( jobDTO.getPostingDate() );
        existingEntity.setApplicationDeadline( jobDTO.getApplicationDeadline() );
        existingEntity.setMinAllowance( jobDTO.getMinAllowance() );
        existingEntity.setMaxAllowance( jobDTO.getMaxAllowance() );
        if ( jobDTO.getNoAllowance() != null ) {
            existingEntity.setNoAllowance( jobDTO.getNoAllowance() );
        }
        existingEntity.setCountry( jobDTO.getCountry() );
        existingEntity.setCity( jobDTO.getCity() );
        existingEntity.setDistrict( jobDTO.getDistrict() );
        existingEntity.setAddress( jobDTO.getAddress() );
        existingEntity.setDescription( jobDTO.getDescription() );
        existingEntity.setRequirements( jobDTO.getRequirements() );
        existingEntity.setBenefits( jobDTO.getBenefits() );
    }

    @Override
    public List<JobShowDTO> toDtoList(List<Job> jobs) {
        if ( jobs == null ) {
            return null;
        }

        List<JobShowDTO> list = new ArrayList<JobShowDTO>( jobs.size() );
        for ( Job job : jobs ) {
            list.add( jobToJobShowDTO( job ) );
        }

        return list;
    }

    protected JobPosition positionDTOToJobPosition(PositionDTO positionDTO) {
        if ( positionDTO == null ) {
            return null;
        }

        JobPosition.JobPositionBuilder jobPosition = JobPosition.builder();

        jobPosition.id( positionDTO.getId() );

        return jobPosition.build();
    }

    protected List<JobPosition> positionDTOListToJobPositionList(List<PositionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobPosition> list1 = new ArrayList<JobPosition>( list.size() );
        for ( PositionDTO positionDTO : list ) {
            list1.add( positionDTOToJobPosition( positionDTO ) );
        }

        return list1;
    }

    protected JobSchedule scheduleDTOToJobSchedule(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        JobSchedule.JobScheduleBuilder jobSchedule = JobSchedule.builder();

        jobSchedule.id( scheduleDTO.getId() );

        return jobSchedule.build();
    }

    protected List<JobSchedule> scheduleDTOListToJobScheduleList(List<ScheduleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobSchedule> list1 = new ArrayList<JobSchedule>( list.size() );
        for ( ScheduleDTO scheduleDTO : list ) {
            list1.add( scheduleDTOToJobSchedule( scheduleDTO ) );
        }

        return list1;
    }

    protected JobMajor majorDTOToJobMajor(MajorDTO majorDTO) {
        if ( majorDTO == null ) {
            return null;
        }

        JobMajor.JobMajorBuilder jobMajor = JobMajor.builder();

        jobMajor.id( majorDTO.getId() );

        return jobMajor.build();
    }

    protected List<JobMajor> majorDTOListToJobMajorList(List<MajorDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<JobMajor> list1 = new ArrayList<JobMajor>( list.size() );
        for ( MajorDTO majorDTO : list ) {
            list1.add( majorDTOToJobMajor( majorDTO ) );
        }

        return list1;
    }

    protected List<PositionDTO> jobPositionListToPositionDTOList(List<JobPosition> list) {
        if ( list == null ) {
            return null;
        }

        List<PositionDTO> list1 = new ArrayList<PositionDTO>( list.size() );
        for ( JobPosition jobPosition : list ) {
            list1.add( positionMapper.jobPositiontoPositionDto( jobPosition ) );
        }

        return list1;
    }

    protected List<ScheduleDTO> jobScheduleListToScheduleDTOList(List<JobSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<ScheduleDTO> list1 = new ArrayList<ScheduleDTO>( list.size() );
        for ( JobSchedule jobSchedule : list ) {
            list1.add( scheduleMapper.toDTO( jobSchedule ) );
        }

        return list1;
    }

    private Company jobHrCompany(Job job) {
        if ( job == null ) {
            return null;
        }
        HR hr = job.getHr();
        if ( hr == null ) {
            return null;
        }
        Company company = hr.getCompany();
        if ( company == null ) {
            return null;
        }
        return company;
    }

    protected JobShowDTO jobToJobShowDTO(Job job) {
        if ( job == null ) {
            return null;
        }

        JobShowDTO.JobShowDTOBuilder jobShowDTO = JobShowDTO.builder();

        jobShowDTO.id( job.getId() );
        jobShowDTO.title( job.getTitle() );
        jobShowDTO.amount( job.getAmount() );
        jobShowDTO.postingDate( job.getPostingDate() );
        jobShowDTO.applicationDeadline( job.getApplicationDeadline() );
        jobShowDTO.minAllowance( job.getMinAllowance() );
        jobShowDTO.maxAllowance( job.getMaxAllowance() );
        jobShowDTO.noAllowance( job.isNoAllowance() );
        jobShowDTO.description( job.getDescription() );
        jobShowDTO.requirements( job.getRequirements() );
        jobShowDTO.benefits( job.getBenefits() );
        jobShowDTO.country( job.getCountry() );
        jobShowDTO.city( job.getCity() );
        jobShowDTO.district( job.getDistrict() );
        jobShowDTO.address( job.getAddress() );

        return jobShowDTO.build();
    }
}

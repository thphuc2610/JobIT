package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.JobScheduleDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobSchedule;
import com.r2s.findInternship.domain.entity.Schedule;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class JobScheduleMapperImpl implements JobScheduleMapper {

    @Override
    public JobScheduleDTO toDto(JobSchedule jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }

        JobScheduleDTO.JobScheduleDTOBuilder jobScheduleDTO = JobScheduleDTO.builder();

        jobScheduleDTO.jobDTO( jobToJobDTO( jobMajor.getJob() ) );
        jobScheduleDTO.scheduleDTO( scheduleToScheduleDTO( jobMajor.getSchedule() ) );
        jobScheduleDTO.id( jobMajor.getId() );

        return jobScheduleDTO.build();
    }

    @Override
    public ScheduleDTO toScheduleDto(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( jobScheduleScheduleId( jobSchedule ) );
        scheduleDTO.name( jobScheduleScheduleName( jobSchedule ) );

        return scheduleDTO.build();
    }

    @Override
    public JobShowDTO toJobShowDto(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }

        JobShowDTO.JobShowDTOBuilder jobShowDTO = JobShowDTO.builder();

        jobShowDTO.id( jobScheduleJobId( jobSchedule ) );
        jobShowDTO.title( jobScheduleJobTitle( jobSchedule ) );
        jobShowDTO.description( jobScheduleJobDescription( jobSchedule ) );
        jobShowDTO.minAllowance( jobScheduleJobMinAllowance( jobSchedule ) );
        jobShowDTO.maxAllowance( jobScheduleJobMaxAllowance( jobSchedule ) );
        jobShowDTO.requirements( jobScheduleJobRequirements( jobSchedule ) );
        jobShowDTO.benefits( jobScheduleJobBenefits( jobSchedule ) );
        jobShowDTO.postingDate( jobScheduleJobPostingDate( jobSchedule ) );
        jobShowDTO.applicationDeadline( jobScheduleJobApplicationDeadline( jobSchedule ) );
        jobShowDTO.noAllowance( jobScheduleJobNoAllowance( jobSchedule ) );
        jobShowDTO.country( jobScheduleJobCountry( jobSchedule ) );
        jobShowDTO.city( jobScheduleJobCity( jobSchedule ) );
        jobShowDTO.district( jobScheduleJobDistrict( jobSchedule ) );
        jobShowDTO.address( jobScheduleJobAddress( jobSchedule ) );
        jobShowDTO.amount( jobScheduleJobAmount( jobSchedule ) );

        return jobShowDTO.build();
    }

    @Override
    public JobSchedule toEntity(JobScheduleDTO jobScheduleDTO) {
        if ( jobScheduleDTO == null ) {
            return null;
        }

        JobSchedule.JobScheduleBuilder jobSchedule = JobSchedule.builder();

        if ( jobScheduleDTO.getId() != null ) {
            jobSchedule.id( jobScheduleDTO.getId() );
        }

        return jobSchedule.build();
    }

    @Override
    public JobScheduleDTO toDTO(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }

        JobScheduleDTO.JobScheduleDTOBuilder jobScheduleDTO = JobScheduleDTO.builder();

        jobScheduleDTO.id( jobSchedule.getId() );

        return jobScheduleDTO.build();
    }

    protected JobDTO jobToJobDTO(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

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

    protected ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO.ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.id( schedule.getId() );
        scheduleDTO.name( schedule.getName() );

        return scheduleDTO.build();
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

    private long jobScheduleJobId(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return 0L;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return 0L;
        }
        long id = job.getId();
        return id;
    }

    private String jobScheduleJobTitle(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String title = job.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String jobScheduleJobDescription(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String description = job.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private Double jobScheduleJobMinAllowance(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        Double minAllowance = job.getMinAllowance();
        if ( minAllowance == null ) {
            return null;
        }
        return minAllowance;
    }

    private Double jobScheduleJobMaxAllowance(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        Double maxAllowance = job.getMaxAllowance();
        if ( maxAllowance == null ) {
            return null;
        }
        return maxAllowance;
    }

    private String jobScheduleJobRequirements(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String requirements = job.getRequirements();
        if ( requirements == null ) {
            return null;
        }
        return requirements;
    }

    private String jobScheduleJobBenefits(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String benefits = job.getBenefits();
        if ( benefits == null ) {
            return null;
        }
        return benefits;
    }

    private Date jobScheduleJobPostingDate(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        Date postingDate = job.getPostingDate();
        if ( postingDate == null ) {
            return null;
        }
        return postingDate;
    }

    private Date jobScheduleJobApplicationDeadline(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        Date applicationDeadline = job.getApplicationDeadline();
        if ( applicationDeadline == null ) {
            return null;
        }
        return applicationDeadline;
    }

    private boolean jobScheduleJobNoAllowance(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return false;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return false;
        }
        boolean noAllowance = job.isNoAllowance();
        return noAllowance;
    }

    private String jobScheduleJobCountry(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String country = job.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String jobScheduleJobCity(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String city = job.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String jobScheduleJobDistrict(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String district = job.getDistrict();
        if ( district == null ) {
            return null;
        }
        return district;
    }

    private String jobScheduleJobAddress(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return null;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return null;
        }
        String address = job.getAddress();
        if ( address == null ) {
            return null;
        }
        return address;
    }

    private int jobScheduleJobAmount(JobSchedule jobSchedule) {
        if ( jobSchedule == null ) {
            return 0;
        }
        Job job = jobSchedule.getJob();
        if ( job == null ) {
            return 0;
        }
        int amount = job.getAmount();
        return amount;
    }
}

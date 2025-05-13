package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobPositionDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobPosition;
import com.r2s.findInternship.domain.entity.Position;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class JobPositionMapperImpl implements JobPositionMapper {

    @Override
    public PositionDTO toPositionDto(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }

        PositionDTO.PositionDTOBuilder positionDTO = PositionDTO.builder();

        positionDTO.id( jobPositionPositionId( jobPosition ) );
        positionDTO.name( jobPositionPositionName( jobPosition ) );

        return positionDTO.build();
    }

    @Override
    public JobShowDTO toJobShowDto(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }

        JobShowDTO.JobShowDTOBuilder jobShowDTO = JobShowDTO.builder();

        jobShowDTO.id( jobPositionJobId( jobPosition ) );
        jobShowDTO.title( jobPositionJobTitle( jobPosition ) );
        jobShowDTO.description( jobPositionJobDescription( jobPosition ) );
        jobShowDTO.minAllowance( jobPositionJobMinAllowance( jobPosition ) );
        jobShowDTO.maxAllowance( jobPositionJobMaxAllowance( jobPosition ) );
        jobShowDTO.requirements( jobPositionJobRequirements( jobPosition ) );
        jobShowDTO.benefits( jobPositionJobBenefits( jobPosition ) );
        jobShowDTO.postingDate( jobPositionJobPostingDate( jobPosition ) );
        jobShowDTO.applicationDeadline( jobPositionJobApplicationDeadline( jobPosition ) );
        jobShowDTO.amount( jobPositionJobAmount( jobPosition ) );
        jobShowDTO.country( jobPositionJobCountry( jobPosition ) );
        jobShowDTO.city( jobPositionJobCity( jobPosition ) );
        jobShowDTO.district( jobPositionJobDistrict( jobPosition ) );
        jobShowDTO.address( jobPositionJobAddress( jobPosition ) );

        return jobShowDTO.build();
    }

    @Override
    public JobPosition toEntity(JobPositionDTO jobPositionDTO) {
        if ( jobPositionDTO == null ) {
            return null;
        }

        JobPosition.JobPositionBuilder jobPosition = JobPosition.builder();

        if ( jobPositionDTO.getId() != null ) {
            jobPosition.id( jobPositionDTO.getId() );
        }

        return jobPosition.build();
    }

    @Override
    public JobPositionDTO toDTO(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }

        JobPositionDTO.JobPositionDTOBuilder jobPositionDTO = JobPositionDTO.builder();

        jobPositionDTO.id( jobPosition.getId() );

        return jobPositionDTO.build();
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

    private long jobPositionJobId(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return 0L;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return 0L;
        }
        long id = job.getId();
        return id;
    }

    private String jobPositionJobTitle(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String title = job.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String jobPositionJobDescription(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String description = job.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private Double jobPositionJobMinAllowance(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        Double minAllowance = job.getMinAllowance();
        if ( minAllowance == null ) {
            return null;
        }
        return minAllowance;
    }

    private Double jobPositionJobMaxAllowance(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        Double maxAllowance = job.getMaxAllowance();
        if ( maxAllowance == null ) {
            return null;
        }
        return maxAllowance;
    }

    private String jobPositionJobRequirements(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String requirements = job.getRequirements();
        if ( requirements == null ) {
            return null;
        }
        return requirements;
    }

    private String jobPositionJobBenefits(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String benefits = job.getBenefits();
        if ( benefits == null ) {
            return null;
        }
        return benefits;
    }

    private Date jobPositionJobPostingDate(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        Date postingDate = job.getPostingDate();
        if ( postingDate == null ) {
            return null;
        }
        return postingDate;
    }

    private Date jobPositionJobApplicationDeadline(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        Date applicationDeadline = job.getApplicationDeadline();
        if ( applicationDeadline == null ) {
            return null;
        }
        return applicationDeadline;
    }

    private int jobPositionJobAmount(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return 0;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return 0;
        }
        int amount = job.getAmount();
        return amount;
    }

    private String jobPositionJobCountry(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String country = job.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String jobPositionJobCity(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String city = job.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String jobPositionJobDistrict(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String district = job.getDistrict();
        if ( district == null ) {
            return null;
        }
        return district;
    }

    private String jobPositionJobAddress(JobPosition jobPosition) {
        if ( jobPosition == null ) {
            return null;
        }
        Job job = jobPosition.getJob();
        if ( job == null ) {
            return null;
        }
        String address = job.getAddress();
        if ( address == null ) {
            return null;
        }
        return address;
    }
}

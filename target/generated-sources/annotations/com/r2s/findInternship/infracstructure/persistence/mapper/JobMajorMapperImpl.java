package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.JobMajorDTO;
import com.r2s.findInternship.application.dto.JobShowDTO;
import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.JobMajor;
import com.r2s.findInternship.domain.entity.Major;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class JobMajorMapperImpl implements JobMajorMapper {

    @Override
    public MajorDTO toMajorDto(Major major) {
        if ( major == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( major.getId() );
        majorDTO.name( major.getName() );

        return majorDTO.build();
    }

    @Override
    public MajorDTO toMajorDto(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }

        MajorDTO.MajorDTOBuilder majorDTO = MajorDTO.builder();

        majorDTO.id( jobMajorMajorId( jobMajor ) );
        majorDTO.name( jobMajorMajorName( jobMajor ) );

        return majorDTO.build();
    }

    @Override
    public JobShowDTO toJobDto(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }

        JobShowDTO.JobShowDTOBuilder jobShowDTO = JobShowDTO.builder();

        jobShowDTO.id( jobMajorJobId( jobMajor ) );
        jobShowDTO.title( jobMajorJobTitle( jobMajor ) );
        jobShowDTO.description( jobMajorJobDescription( jobMajor ) );
        jobShowDTO.minAllowance( jobMajorJobMinAllowance( jobMajor ) );
        jobShowDTO.maxAllowance( jobMajorJobMaxAllowance( jobMajor ) );
        jobShowDTO.requirements( jobMajorJobRequirements( jobMajor ) );
        jobShowDTO.benefits( jobMajorJobBenefits( jobMajor ) );
        jobShowDTO.postingDate( jobMajorJobPostingDate( jobMajor ) );
        jobShowDTO.applicationDeadline( jobMajorJobApplicationDeadline( jobMajor ) );
        jobShowDTO.amount( jobMajorJobAmount( jobMajor ) );
        jobShowDTO.country( jobMajorJobCountry( jobMajor ) );
        jobShowDTO.city( jobMajorJobCity( jobMajor ) );
        jobShowDTO.district( jobMajorJobDistrict( jobMajor ) );
        jobShowDTO.address( jobMajorJobAddress( jobMajor ) );

        return jobShowDTO.build();
    }

    @Override
    public List<MajorDTO> toMajorDtoList(List<JobMajor> jobMajors) {
        if ( jobMajors == null ) {
            return null;
        }

        List<MajorDTO> list = new ArrayList<MajorDTO>( jobMajors.size() );
        for ( JobMajor jobMajor : jobMajors ) {
            list.add( toMajorDto( jobMajor ) );
        }

        return list;
    }

    @Override
    public JobMajor toEntity(JobMajorDTO jobMajorDTO) {
        if ( jobMajorDTO == null ) {
            return null;
        }

        JobMajor.JobMajorBuilder jobMajor = JobMajor.builder();

        if ( jobMajorDTO.getId() != null ) {
            jobMajor.id( jobMajorDTO.getId() );
        }

        return jobMajor.build();
    }

    @Override
    public JobMajorDTO toDTO(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }

        JobMajorDTO.JobMajorDTOBuilder jobMajorDTO = JobMajorDTO.builder();

        jobMajorDTO.id( jobMajor.getId() );

        return jobMajorDTO.build();
    }

    private int jobMajorMajorId(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return 0;
        }
        Major major = jobMajor.getMajor();
        if ( major == null ) {
            return 0;
        }
        int id = major.getId();
        return id;
    }

    private String jobMajorMajorName(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Major major = jobMajor.getMajor();
        if ( major == null ) {
            return null;
        }
        String name = major.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private long jobMajorJobId(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return 0L;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return 0L;
        }
        long id = job.getId();
        return id;
    }

    private String jobMajorJobTitle(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String title = job.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String jobMajorJobDescription(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String description = job.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private Double jobMajorJobMinAllowance(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        Double minAllowance = job.getMinAllowance();
        if ( minAllowance == null ) {
            return null;
        }
        return minAllowance;
    }

    private Double jobMajorJobMaxAllowance(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        Double maxAllowance = job.getMaxAllowance();
        if ( maxAllowance == null ) {
            return null;
        }
        return maxAllowance;
    }

    private String jobMajorJobRequirements(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String requirements = job.getRequirements();
        if ( requirements == null ) {
            return null;
        }
        return requirements;
    }

    private String jobMajorJobBenefits(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String benefits = job.getBenefits();
        if ( benefits == null ) {
            return null;
        }
        return benefits;
    }

    private Date jobMajorJobPostingDate(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        Date postingDate = job.getPostingDate();
        if ( postingDate == null ) {
            return null;
        }
        return postingDate;
    }

    private Date jobMajorJobApplicationDeadline(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        Date applicationDeadline = job.getApplicationDeadline();
        if ( applicationDeadline == null ) {
            return null;
        }
        return applicationDeadline;
    }

    private int jobMajorJobAmount(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return 0;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return 0;
        }
        int amount = job.getAmount();
        return amount;
    }

    private String jobMajorJobCountry(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String country = job.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String jobMajorJobCity(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String city = job.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String jobMajorJobDistrict(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
        if ( job == null ) {
            return null;
        }
        String district = job.getDistrict();
        if ( district == null ) {
            return null;
        }
        return district;
    }

    private String jobMajorJobAddress(JobMajor jobMajor) {
        if ( jobMajor == null ) {
            return null;
        }
        Job job = jobMajor.getJob();
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

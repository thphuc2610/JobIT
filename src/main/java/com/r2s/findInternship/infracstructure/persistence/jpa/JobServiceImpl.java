package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.domain.common.MessageResponse;

import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.common.util.ExcelHelper;
import com.r2s.findInternship.domain.entity.*;
import com.r2s.findInternship.domain.repository.*;

import com.r2s.findInternship.domain.repository.specification.JobSpecification;
import com.r2s.findInternship.domain.service.*;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;

import com.r2s.findInternship.infracstructure.persistence.mapper.JobMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final UserService userService;
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final StatusService statusService;
    private final StatusRepository statusRepository;
    private final JobPositionService jobPositionService;
    private final JobScheduleService jobScheduleService;
    private final JobMajorService jobMajorService;
    private final PositionRepository positionRepository;
    private final JobPositionRepository jobPositionRepository;
    private final MajorRepository majorRepository;
    private final JobMajorRepository jobMajorRepository;
    private final ScheduleRepository scheduleRepository;
    private final JobScheduleRepository jobScheduleRepository;
    private final ExcelHelper excelHelper;
    private final HRRepository hrRepository;
    private final CompanyService companyService;
    private final CandidateApplicationRepository candidateApplicationRepository;
    private final HttpServletRequest request;
    private static final Logger LOGGER = LoggerFactory.getLogger("info");
    public static final int JOB_STATUS_ACTIVE_ID = 1;

    // Get
    @Override
    public PaginationDTO findByCompanyId(Long companyId, int no, int limit) {
        Page<Object> page = jobRepository.findByHr_Company_Id(companyId, PageRequest.of(no, limit))
                .map(jobMapper::toDTOShow);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public Optional<JobDTO> findById(long id) {
        return jobRepository.findById(id)
                .map(jobMapper::toDTOShow);
    }

    @Override
    public PaginationDTO findByAll(int no, int limit) {
        Page<Object> page = jobRepository.findAll(PageRequest.of(no, limit))
                .map(jobMapper::toDTOShow);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public PaginationDTO findByAllActive(int no, int limit) {
        Page<Object> page = jobRepository.findAllActive(PageRequest.of(no, limit))
                .map(jobMapper::toDTOShow);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public PaginationDTO filterJob(JobFilterDTO jobFilterDTO, int no, int limit) {
        Specification<Job> spec = JobSpecification.filterJobs(jobFilterDTO);

        Page<Object> page = jobRepository.findAll(spec, PageRequest.of(no, limit, Sort.by("postingDate").descending()))
                .map(jobMapper::toDTOShow);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public MessageResponse countJobsByStatusOnDate(Boolean isStatus, LocalDate date) {
        int statusId = isStatus ? 1 : 2;
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy trạng thái: " + statusId));

        Date createdDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Job> jobs = jobRepository.findByStatusAndCreatedDate(status.getId(), createdDate);

        String statusText = (statusId == 1) ? "đã mở" : "đã đóng";

        String message = String.format("Số lượng tin tuyển dụng %s trong ngày %s: %d",
                statusText, date, jobs.size());

        return new MessageResponse(HttpServletResponse.SC_OK, message, request.getServletPath());
    }

    @Override
    public MessageResponse countJobsInMonth(int month, int year) {
        Long jobCount = jobRepository.countJobsInMonth(month, year);

        return new MessageResponse(HttpServletResponse.SC_OK,
                String.format("Tổng số tin đăng trong tháng %d/%d: %d", month, year, jobCount),
                request.getServletPath());
    }

    @Override
    public MessageResponse countJobsByPositionInMonth(int month, int year) {
        List<Object[]> results = jobRepository.countJobsByPositionInMonth(year, month);

        String message = results.stream()
                .map(result -> result[0] + ": " + result[1])
                .collect(Collectors.joining(", "));

        return new MessageResponse(HttpServletResponse.SC_OK, message, request.getServletPath());
    }

    // Post
    @Override
    public JobDTO createJob(JobCreationDTO jobCreationDTO) {
        Long userId = userService.getCurrentUserId();
        HR hr = hrRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with HrId: ", userId)));

        Company company = hr.getCompany();
        if (company == null) {
            throw new ResourceNotFoundException("HR does not belong to any Company");
        }

        Job job = jobMapper.toEntity(jobCreationDTO);
        job.setStatus(statusService.findByName(Estatus.Active));
        job.setHr(hr);
        Job savedJob = jobRepository.save(job);

        List<JobPosition> jobPositions = new ArrayList<>();
        for (PositionDTO position : jobCreationDTO.getPositionDTOS()) {
            jobPositions.add(jobPositionService.createdJobPosition(savedJob, position));
        }

        List<JobMajor> jobMajors = new ArrayList<>();
        for (MajorDTO major : jobCreationDTO.getMajorDTOS()) {
            jobMajors.add(jobMajorService.createdJobMajor(savedJob, major));
        }

        List<JobSchedule> jobSchedules = new ArrayList<>();
        for (ScheduleDTO schedule : jobCreationDTO.getScheduleDTOS()) {
            jobSchedules.add(jobScheduleService.createdJobSchedule(savedJob, schedule));
        }

        savedJob.setJobPositions(jobPositions);
        savedJob.setJobMajors(jobMajors);
        savedJob.setJobSchedules(jobSchedules);

        Job finalSavedJob = jobRepository.findById(savedJob.getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Job not found with id: ", savedJob.getId())));

        LOGGER.info("Post job successfully");
        return jobMapper.toDTOShow(finalSavedJob);
    }

    // Nhân bản
    @Override
    public JobDTO duplicateJob(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HR hr = hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        Job oldJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with job id: ", id)));

        Long existingCompanyId = Optional.ofNullable(oldJob.getHr())
                .map(HR::getCompany)
                .map(Company::getId)
                .orElseThrow(() -> new AccessDeniedException("FORBIDDEN"));

        if (!Objects.equals(existingCompanyId, hr.getCompany().getId())) {
            throw new AccessDeniedException("FORBIDDEN");
        }

        // Tạo đối tượng Job mới (bản sao)
        Job duplicate = new Job();
        jobMapper.updateJobFromJobDTO(jobMapper.toDTOShow(oldJob), duplicate);
        duplicate.setId(0L); // Reset ID
        duplicate.setHr(oldJob.getHr());
        duplicate.setStatus(statusRepository.findById(Estatus.activeStatus)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Status not found with id: ", Estatus.activeStatus))));
        duplicate.setCreatedDate(new Date());
        Job saveDuplicate = jobRepository.save(duplicate);

        oldJob.getJobPositions().forEach(jobPosition -> {
            JobPosition newJobPosition = new JobPosition();
            newJobPosition.setJob(saveDuplicate);
            newJobPosition.setPosition(jobPosition.getPosition());
            jobPositionRepository.save(newJobPosition);
        });

        oldJob.getJobMajors().forEach(jobMajor -> {
            JobMajor newJobMajor = new JobMajor();
            newJobMajor.setJob(saveDuplicate);
            newJobMajor.setMajor(jobMajor.getMajor());
            jobMajorRepository.save(newJobMajor);
        });

        oldJob.getJobSchedules().forEach(jobSchedule -> {
            JobSchedule newJobSchedule = new JobSchedule();
            newJobSchedule.setJob(saveDuplicate);
            newJobSchedule.setSchedule(jobSchedule.getSchedule());
            jobScheduleRepository.save(newJobSchedule);
        });

        return jobMapper.toDTOShow(saveDuplicate);
    }

    // Đóng hoặc mở tin tuyển dụng
    @Override
    public MessageResponse jobStatus(Long id, Boolean isStatus) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HR hr = hrRepository.findByUsername(username).orElseThrow(AccessDeniedExceptionV2::new);

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Not found with job id: ", id)));

        if (!job.getCreatedBy().equals(hr.getId())) throw new AccessDeniedExceptionV2();

        Status status = statusRepository.findByName(isStatus ? Estatus.Active.toString() : Estatus.Not_Active.toString())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with status: ",
                                isStatus ? Estatus.Active.toString() : Estatus.Not_Active.toString())));

        job.setStatus(status);
        job.setCreatedDate(new Date());
        jobRepository.save(job);

        return new MessageResponse(HttpServletResponse.SC_OK,
                String.format("Tin tuyển dụng đã được %s thành công.", isStatus ? "mở" : "đóng"),
                request.getServletPath());
    }

    // Put
    @Override
    public JobDTO updateJob(long id, JobDTO jobUpdateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        Job oldJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with Job id: ", id)));

        if (oldJob.getHr().getCompany().getId() == hr.getCompany().getId()) {
            if (oldJob.getStatus().getId() == JOB_STATUS_ACTIVE_ID) {
                Job newJob = jobMapper.jobUpdateDTOToJob(jobUpdateDTO);
                newJob.setId(oldJob.getId());
                newJob.setStatus(oldJob.getStatus());

                newJob.setHr(oldJob.getHr());
                newJob.setJobMajors(updateJobMajors(newJob, jobUpdateDTO.getMajorDTOS()));
                newJob.setJobPositions(updateJobPositions(newJob, jobUpdateDTO.getPositionDTOS()));
                newJob.setJobSchedules(updateJobSchedules(newJob, jobUpdateDTO.getScheduleDTOS()));
                newJob = this.jobRepository.save(newJob);
                return this.jobMapper.toDTOShow(newJob);
            } else {
                throw new IllegalArgumentException("BAD_REQUEST");
            }
        } else {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }


    private List<JobMajor> updateJobMajors(Job job, List<MajorDTO> majorDTOs) {
        Queue<JobMajor> newJobMajors = new LinkedList<>();
        for (MajorDTO majorDTO : majorDTOs) {
            Major existingMajor = majorRepository.findById(majorDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Major not found"));

            JobMajor newJobMajor = new JobMajor();
            newJobMajor.setJob(job);
            newJobMajor.setMajor(existingMajor);
            newJobMajors.add(newJobMajor);
            job.getJobMajors().add(newJobMajor);
        }

        List<JobMajor> oldJobMajors = jobMajorRepository.findAllByJob_Id(job.getId());
        Iterator<JobMajor> iterator = oldJobMajors.iterator();
        while (iterator.hasNext()) {
            JobMajor oldJobMajor = iterator.next();
            JobMajor newJobMajor = newJobMajors.poll();

            if (newJobMajor == null) {
                jobMajorRepository.deleteById(oldJobMajor.getId());
            } else {
                newJobMajor.setId(oldJobMajor.getId());
                jobMajorRepository.save(newJobMajor);
                iterator.remove();
            }
        }

        while (!newJobMajors.isEmpty()) {
            jobMajorRepository.save(newJobMajors.poll());
        }
        List<JobMajor> jobMajorList = new ArrayList<>();
        jobMajorList.addAll(newJobMajors);

        return jobMajorList;
    }

    private List<JobPosition> updateJobPositions(Job job, List<PositionDTO> positionDTOs) {
        Queue<JobPosition> newJobPositions = new LinkedList<>();
        for (PositionDTO positionDTO : positionDTOs) {
            Position existingPosition = positionRepository.findById(positionDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

            JobPosition newJobPosition = new JobPosition();
            newJobPosition.setJob(job);
            newJobPosition.setPosition(existingPosition);
            newJobPositions.add(newJobPosition);
            job.getJobPositions().add(newJobPosition);
        }
        List<JobPosition> oldJobPositions = jobPositionRepository.findAllByJob_Id(job.getId());
        Iterator<JobPosition> iterator = oldJobPositions.iterator();
        while (iterator.hasNext()) {
            JobPosition oldJobPosition = iterator.next();
            JobPosition newJobPosition = newJobPositions.poll();

            if (newJobPosition == null) {
                jobPositionRepository.deleteById(oldJobPosition.getId());
            } else {
                newJobPosition.setId(oldJobPosition.getId());
                jobPositionRepository.save(newJobPosition);
                iterator.remove();
            }
        }

        while (!newJobPositions.isEmpty()) {
            jobPositionRepository.save(newJobPositions.poll());
        }
        List<JobPosition> jobPositionList = new ArrayList<>();
        jobPositionList.addAll(jobPositionList);

        return jobPositionList;
    }

    private List<JobSchedule> updateJobSchedules(Job job, List<ScheduleDTO> scheduleDTOs) {
        Queue<JobSchedule> newJobSchedules = new LinkedList<>();
        for (ScheduleDTO scheduleDTO : scheduleDTOs) {
            Schedule existingSchedule = scheduleRepository.findById(scheduleDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

            JobSchedule newJobSchedule = new JobSchedule();
            newJobSchedule.setJob(job);
            newJobSchedule.setSchedule(existingSchedule);
            newJobSchedules.add(newJobSchedule);
            job.getJobSchedules().add(newJobSchedule);
        }
        List<JobSchedule> oldJobSchedules = jobScheduleRepository.findAllByJob_Id(job.getId());
        Iterator<JobSchedule> iterator = oldJobSchedules.iterator();
        while (iterator.hasNext()) {
            JobSchedule oldJobSchedule = iterator.next();
            JobSchedule newJobSchedule = newJobSchedules.poll();

            if (newJobSchedule == null) {
                jobScheduleRepository.deleteById(oldJobSchedule.getId());
            } else {
                newJobSchedule.setId(oldJobSchedule.getId());
                jobScheduleRepository.save(newJobSchedule);
                iterator.remove();
            }
        }

        while (!newJobSchedules.isEmpty()) {
            jobScheduleRepository.save(newJobSchedules.poll());
        }

        List<JobSchedule> jobScheduleList = new ArrayList<>();
        jobScheduleList.addAll(jobScheduleList);

        return jobScheduleList;
    }

    // Delete
    @Override
    @Transactional
    public MessageResponse deleteById(Long id) {
        Job entity = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Not found with id: ", id)));

        Long currenUser = userService.getCurrentUserId();

        if (!entity.getCreatedBy().equals(currenUser)) throw new AccessDeniedExceptionV2();

        jobPositionRepository.deleteByJob(entity);
        jobMajorRepository.deleteByJob(entity);
        jobScheduleRepository.deleteByJob(entity);
        jobRepository.delete(entity);

        return new MessageResponse(
                HttpServletResponse.SC_OK,
                "Deleted Internship Programme successfully",
                request.getServletPath()
        );
    }

    @Override
    public Long recruitmentNews(int month) {
        return this.jobRepository.recruitmentNews(month);
    }

    @Override
    public boolean isAppliable(JobDTO jobDTO) {
        return jobDTO.getStatusDTO().getName().equals(String.valueOf(Estatus.Active));
    }

    @Override
    public List<JobDTO> createByExcelFile(MultipartFile file) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));
        try {
            // Check version before read excel file
            boolean isValid = excelHelper.checkFileVersion(file.getInputStream());
            if (isValid) {
                // Read and map job from excel file
                List<Job> jobs = excelHelper.excelToJob(file.getInputStream());

                for (Job job : jobs) {
                    if (job != null) {
                        // set value and save a new job
                        job.setCreatedDate(new Date());
                        job.setStatus(statusService.findByName(Estatus.Active));
                        job.setHr(hr);

                        Job newJob = jobRepository.save(job);

                        // Set list positions, schedules, majors to new job
                        addJobMajors(newJob,
                                job.getJobMajors().stream().map(JobMajor::getMajor).collect(Collectors.toList()));
                        addJobPositions(newJob, job.getJobPositions().stream().map(JobPosition::getPosition)
                                .collect(Collectors.toList()));
                        addJobSchedules(newJob, job.getJobSchedules().stream().map(JobSchedule::getSchedule)
                                .collect(Collectors.toList()));
                    }
                }
                return jobs.stream().map(job -> jobMapper.toDTOShow(job)).collect(Collectors.toList());
            } else
                throw new IOException("This excel file is outdated! Please download the newest version!");
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());

        }
    }

    private void addJobMajors(Job job, List<Major> majors) {
        if (majors != null && majors.size() > 0) {
            for (Major major : majors) {
                Major existingMajor = majorRepository.findByName(major.getName());
                JobMajor newJobMajor = new JobMajor();
                newJobMajor.setMajor(existingMajor);
                newJobMajor.setJob(job);
                job.getJobMajors().add(newJobMajor);
                jobMajorRepository.save(newJobMajor);
            }
        }
    }

    private void addJobPositions(Job job, List<Position> positions) {
        if (positions != null && positions.size() > 0) {
            for (Position position : positions) {
                Position existingPosition = positionRepository.findByName((position.getName()));
                JobPosition newJobPosition = new JobPosition();
                newJobPosition.setPosition(existingPosition);
                newJobPosition.setJob(job);
                job.getJobPositions().add(newJobPosition);
                jobPositionRepository.save(newJobPosition);
            }
        }

    }

    private void addJobSchedules(Job job, List<Schedule> schedules) {
        if (schedules != null && schedules.size() > 0) {
            for (Schedule schedule : schedules) {
                Schedule existingSchedule = scheduleRepository.findByName(schedule.getName());
                JobSchedule newJobSchedule = new JobSchedule();
                newJobSchedule.setSchedule(existingSchedule);
                newJobSchedule.setJob(job);
                job.getJobSchedules().add(newJobSchedule);
                jobScheduleRepository.save(newJobSchedule);
            }
        }
    }
}
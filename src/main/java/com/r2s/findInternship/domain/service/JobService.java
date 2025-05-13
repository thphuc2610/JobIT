package com.r2s.findInternship.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.domain.common.MessageResponse;

import org.springframework.web.multipart.MultipartFile;

public interface JobService {
    Long recruitmentNews(int month);

    boolean isAppliable(JobDTO jobDTO);

    List<JobDTO> createByExcelFile(MultipartFile file);

    // Get
    PaginationDTO findByCompanyId(Long companyId, int no, int limit);

    Optional<JobDTO> findById(long id);

    PaginationDTO findByAll(int no, int limit);

    PaginationDTO findByAllActive(int no, int limit);

    PaginationDTO filterJob(JobFilterDTO jobFilterDTO, int no, int limit);

    MessageResponse countJobsByStatusOnDate(Boolean isStatus, LocalDate date);

    MessageResponse countJobsInMonth(int month, int year);

    MessageResponse countJobsByPositionInMonth(int month, int year);

    // Post
    JobDTO createJob(JobCreationDTO jobCreationDTO);

    JobDTO duplicateJob(Long id);

    // Put
    JobDTO updateJob(long id, JobDTO jobDTO);

    MessageResponse jobStatus(Long id, Boolean isStatus);

    // Delete
    MessageResponse deleteById(Long id);
}
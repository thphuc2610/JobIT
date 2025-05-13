package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.JobMajor;

import java.util.List;

@Repository
public interface JobMajorRepository extends JpaRepository<JobMajor, Long> {
    List<JobMajor> findAllByJob_Id(long jobId);

    void deleteByJob(Job job);
}
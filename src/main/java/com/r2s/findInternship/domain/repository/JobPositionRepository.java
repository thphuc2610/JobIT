package com.r2s.findInternship.domain.repository;
import com.r2s.findInternship.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.JobPosition;

import java.util.List;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    List<JobPosition> findAllByJob_Id(long jobId);

    void deleteByJob(Job job);
}
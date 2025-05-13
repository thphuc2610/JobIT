package com.r2s.findInternship.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.JobCare;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobCareRepository extends JpaRepository<JobCare, Long> {
    Optional<JobCare> findByJobIdAndCandidateId(long jobId, long candidateId);

    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    Optional<JobCare> findByCandidateIdAndJobId(@Param("candidateId") long candidateId, @Param("jobId") long jobId);

    @Query("SELECT jc FROM JobCare jc WHERE jc.candidate.id = :candidateId ORDER BY jc.id DESC")
    Page<JobCare> findAllByCandidateId(@Param("candidateId") long candidateId, Pageable pageable);

    @Query("SELECT jc.job.id FROM JobCare jc WHERE jc.candidate.id = :candidateId GROUP BY jc.job.id")
    List<Integer> findJobSave(@Param("candidateId") long candidateId);
}
package com.r2s.findInternship.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.CandidateApplication;

@Repository
public interface CandidateApplicationRepository extends JpaRepository<CandidateApplication, Long> {
    Page<CandidateApplication> findAllByCandidateIdOrderByCreatedDateDesc(@Param("candidateId") long candidateId,
                                                                          Pageable pageable);

    @Query("SELECT ca FROM CandidateApplication ca WHERE ca.job.id = :jobId ORDER BY ca.createdDate DESC")
    Page<CandidateApplication> findAllByJobId(@Param("jobId") long jobId, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(ca.id) > 0 THEN TRUE ELSE FALSE END FROM CandidateApplication ca"
            + " WHERE ca.candidate.id = :candidateId AND ca.job.id = :jobId")
    boolean existsByCandidateIdAndJobId(@Param("candidateId") long candidateId, @Param("jobId") long jobId);
}
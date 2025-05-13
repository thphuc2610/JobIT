package com.r2s.findInternship.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>,
                JpaSpecificationExecutor<Candidate> {
        @Query("SELECT c FROM Candidate c"
                        + " WHERE c.id IN (SELECT ca.candidate.id FROM CandidateApplication ca WHERE ca.job.id = :jobId)")
        Page<Candidate> findByJobId(@Param("jobId") int jobId, Pageable pageable);

        @Query("SELECT c FROM Candidate c WHERE c.user.id = :userId")
        Optional<Candidate> findByUserId(@Param("userId") long userId);

        @Query("SELECT c FROM Candidate c"
                        + " JOIN c.candidateSchedules cs"
                        + " JOIN c.candidatePositions cp"
                        + " JOIN c.candidateMajors cm"
                        + " WHERE c.searchable = true"
                        + " AND (:desiredJob IS NULL OR c.desiredJob LIKE %:desiredJob%)"
                        + " AND (:desiredWorkingProvince IS NULL OR c.desiredWorkingProvince LIKE %:desiredWorkingProvince%)"
                        + " AND (:scheduleIds IS NULL OR cs.schedule.id IN :scheduleIds)"
                        + " AND (:positionIds IS NULL OR cp.position.id IN :positionIds)"
                        + " AND (:majorIds IS NULL OR cm.major.id IN :majorIds)"
                        + " GROUP BY c")
        Page<Candidate> filterByHr(String desiredJob, String desiredWorkingProvince,
                        List<Integer> scheduleIds, List<Integer> positionIds, List<Integer> majorIds,
                        Pageable pageable);
}
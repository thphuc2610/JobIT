package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.HRSaveCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRSaveCandidateRepository extends JpaRepository<HRSaveCandidate, Long> {
    Page<HRSaveCandidate> findAllByHrId(Long hrId, Pageable pageable);

    boolean existsByCandidateIdAndHrId(Long candidateId, Long hrId);

    void deleteByCandidateIdAndHrId(Long candidateId, Long hrId);
}
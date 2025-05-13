package com.r2s.findInternship.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.CandidatePosition;

@Repository
public interface CandidatePositionRepository extends JpaRepository<CandidatePosition, Long> {
    List<CandidatePosition> findAllByCandidate_Id(long candidateId);
}
package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.findInternship.domain.entity.CandidateMajor;

import java.util.List;

public interface CandidateMajorRepository extends JpaRepository<CandidateMajor, Long> {
    List<CandidateMajor> findAllByCandidate_Id(long candidateId);
}
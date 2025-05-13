package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.CandidateSchedule;

import java.util.List;

@Repository
public interface CandidateScheduleRepository extends JpaRepository<CandidateSchedule, Long> {
    List<CandidateSchedule> findAllByCandidate_Id(long candidateId);
}
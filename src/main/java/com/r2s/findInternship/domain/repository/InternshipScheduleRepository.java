package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.InternshipSchedule;

@Repository
public interface InternshipScheduleRepository extends JpaRepository<InternshipSchedule, Long> {
}

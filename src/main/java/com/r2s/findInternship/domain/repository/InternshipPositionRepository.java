package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.InternshipPosition;

@Repository
public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {
    InternshipPosition findByInternshipProgrammeAndPosition(InternshipProgramme internshipProgramme, Position position);
    void deleteByInternshipProgramme(InternshipProgramme internshipProgramme);
    void deleteByInternshipProgrammeId(Long internshipProgrammeId);
}
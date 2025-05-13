package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.InternshipMajor;

@Repository
public interface InternshipMajorRepository extends JpaRepository<InternshipMajor, Long> {
    InternshipMajor findByInternshipProgrammeAndMajor(InternshipProgramme internshipProgramme, Major major);
    void deleteByInternshipProgramme(InternshipProgramme internshipProgramme);
    void deleteByInternshipProgrammeId(Long internshipProgrammeId);
}
package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.HRSaveInternshipProgramme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRSaveInternshipProgrammeRepository extends JpaRepository<HRSaveInternshipProgramme, Long> {
    Page<HRSaveInternshipProgramme> findAllByHrId(Long hrId, Pageable pageable);

    boolean existsByInternshipProgrammeIdAndHrId(Long internshipProgrammeId, Long hrId);

    void deleteByInternshipProgrammeIdAndHrId(Long internshipProgrammeId, Long hrId);
}
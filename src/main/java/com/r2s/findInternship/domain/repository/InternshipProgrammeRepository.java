
package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.InternshipProgramme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Repository
public interface InternshipProgrammeRepository extends JpaRepository<InternshipProgramme, Long>,
        JpaSpecificationExecutor<InternshipProgramme> {
    @Query("SELECT ip FROM InternshipProgramme ip WHERE ip.status.id = 1 ORDER BY ip.createdDate DESC")
    Page<InternshipProgramme> findAllActive(Pageable pageable);

    Page<InternshipProgramme> findAllByTitleLike(String name, Pageable pageable);

    Page<InternshipProgramme> findAllActiveByUniversityId(@Param("universityId") Long universityId, Pageable pageable);

    // Page<InternshipProgramme> findAll(Specification<InternshipProgramme> filter, Pageable pageable);

    @Query("SELECT ip FROM InternshipProgramme ip " +
            "WHERE (:title IS NULL OR LOWER(ip.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:location IS NULL OR LOWER(ip.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND (:startDate IS NULL OR ip.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR ip.endDate <= :endDate) " +
            "AND (:statusId IS NULL OR (ip.status.id) = (:statusId))" +
            "AND (:startDate IS NULL OR :endDate IS NULL OR (ip.startDate <= :endDate AND ip.endDate >= :startDate)) ")
    Page<InternshipProgramme> searchInternshipProgrammes(
            @Param("title") String title,
            @Param("location") String location,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("statusId") Integer statusId,
            Pageable pageable);
}
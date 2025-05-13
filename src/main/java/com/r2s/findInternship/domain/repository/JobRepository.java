package com.r2s.findInternship.domain.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    Page<Job> findByHr_Company_Id(Long companyId, Pageable pageable);

    Page<Job> findAll(Specification<Job> filter, Pageable pageable);

    boolean existsByIdAndHrId(Long jobId, Long hrId);

    List<Job> findByStatusAndCreatedDate(Status status, LocalDate createdDate);

    @Query("SELECT j FROM Job j WHERE j.status.id = 1")
    List<Job> findJobActive();

    @Query("SELECT COUNT(j) FROM Job j WHERE MONTH(j.postingDate) = :month")
    Long recruitmentNews(@Param("month") int month);

    @Query(value = "SELECT j FROM Job j WHERE j.status.id = 1 ORDER BY j.createdDate DESC")
    Page<Job> findAllActive(Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.status.id = :statusId AND FUNCTION('DATE', j.createdDate) = :createdDate")
    List<Job> findByStatusAndCreatedDate(@Param("statusId") int statusId, @Param("createdDate") Date createdDate);

    @Query("SELECT COUNT(j) FROM Job j WHERE MONTH(j.postingDate) = :month AND YEAR(j.postingDate) = :year")
    Long countJobsInMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT jp.position.name, COUNT(j) " +
            "FROM Job j JOIN j.jobPositions jp " +
            "WHERE YEAR(j.postingDate) = :year AND MONTH(j.postingDate) = :month " +
            "GROUP BY jp.position.name " +
            "ORDER BY COUNT(j) DESC")
    List<Object[]> countJobsByPositionInMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT j.status.name, COUNT(j.id) FROM Job j GROUP BY j.status.name")
    List<Object[]> getStatusStatistics();
}
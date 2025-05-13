package com.r2s.findInternship.domain.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.CompanyRate;

@Transactional
@Repository
public interface CompanyRateRepository extends JpaRepository<CompanyRate, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM CompanyRate cr WHERE cr.company.id = :companyId")
    void deleteByCompanyId(@Param("companyId") Long companyId);
}
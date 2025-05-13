package com.r2s.findInternship.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByUniversityId(Long universityId);
    Optional<Partner> findByUserId(Long userId);
}
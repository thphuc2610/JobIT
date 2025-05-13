package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.PartnerJobCare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerJobCareRepository extends JpaRepository<PartnerJobCare, Long> {
    boolean existsByPartnerIdAndJobId(Long partnerId, Long jobId);

    Page<PartnerJobCare> findAllByPartnerId(Long partnerId, Pageable pageable);

    @Query("SELECT pjc.job.id FROM PartnerJobCare pjc WHERE pjc.partner.id = :partnerId GROUP BY pjc.job.id")
    List<Long> findJobSave( Long partnerId);

    Optional<PartnerJobCare> findByPartnerIdAndJobId(Long partnerId, Long jobId);

    Optional<PartnerJobCare> findByJobIdAndPartnerId(Long jobId, Long partnerId);
}
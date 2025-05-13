package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerJobCareDTO;
import com.r2s.findInternship.domain.common.MessageResponse;

import java.util.List;

public interface PartnerJobCareService {
    PaginationDTO findAllByPartnerId(Long partnerId, int no, int limit);

    List<JobDTO> findJobSaveOfPartnerId();

    PaginationDTO findAllByJobId(Long jobId, int no, int limit);

    List<PartnerJobCareDTO> findAll();

    PartnerJobCareDTO findById(Long id);

    PartnerJobCareDTO findByPartnerIdAndJobId(Long partnerId, Long jobId);

    MessageResponse create(Long JobId);

    MessageResponse deleteByJobIdAndPartnerId(Long JobId);

    boolean existsByJobIdAndPartnerId(Long JobId, Long partnerId);
}
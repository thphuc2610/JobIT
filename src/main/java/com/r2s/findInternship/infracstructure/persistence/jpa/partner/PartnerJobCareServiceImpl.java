package com.r2s.findInternship.infracstructure.persistence.jpa.partner;

import com.r2s.findInternship.application.dto.JobDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerJobCareDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.Job;
import com.r2s.findInternship.domain.entity.Partner;
import com.r2s.findInternship.domain.entity.PartnerJobCare;
import com.r2s.findInternship.domain.repository.JobRepository;
import com.r2s.findInternship.domain.repository.PartnerJobCareRepository;
import com.r2s.findInternship.domain.repository.PartnerRepository;
import com.r2s.findInternship.domain.service.JobService;
import com.r2s.findInternship.domain.service.PartnerJobCareService;
import com.r2s.findInternship.domain.service.PartnerService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ConflictExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.JobMapper;
import com.r2s.findInternship.infracstructure.persistence.mapper.PartnerJobCareMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerJobCareServiceImpl implements PartnerJobCareService {
    private final PartnerRepository partnerRepository;
    private final PartnerService partnerService;
    private final PartnerJobCareMapper partnerJobCareMapper;
    private final PartnerJobCareRepository partnerJobCareRepository;
    private final JobService jobService;
    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public PaginationDTO findAllByPartnerId(Long partnerId, int no, int limit) {
        Page<PartnerJobCareDTO> page = this.partnerJobCareRepository
                .findAllByPartnerId(partnerId, PageRequest.of(no, limit))
                .map(partnerJobCareMapper::toDTO);

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public List<JobDTO> findJobSaveOfPartnerId() {
        Partner partner = partnerRepository.findByUserId(userService.getCurrentUserId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Partner not found", userService.getCurrentUserId())
                ));

        List<Long> listJobId = this.partnerJobCareRepository.findJobSave(partner.getId());
        if (listJobId.isEmpty()) return Collections.emptyList();

        return jobRepository.findAllById(listJobId).stream()
                .map(jobMapper::toDTOShow)
                .toList();
    }

    @Override
    public PaginationDTO findAllByJobId(Long jobId, int no, int limit) {
        return null;
    }

    @Override
    public List<PartnerJobCareDTO> findAll() {
        return this.partnerJobCareRepository.findAll()
                .stream().map(partnerJobCareMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartnerJobCareDTO findById(Long id) {
        PartnerJobCare partnerJobCare = this.partnerJobCareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Partner job care not found with id: ", id)
                ));

        return this.partnerJobCareMapper.toDTO(partnerJobCare);
    }

    @Override
    public PartnerJobCareDTO findByPartnerIdAndJobId(Long partnerId, Long jobId) {
        PartnerJobCare partnerJobCare = this.partnerJobCareRepository.findByPartnerIdAndJobId(partnerId, jobId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap(
                        "Error", "Partner Job care not found for (partnerId, jobId): (" + partnerId + ", " + jobId + ")"
                )));

        return this.partnerJobCareMapper.toDTO(partnerJobCare);
    }

    @Override
    public MessageResponse create(Long jobId) {
        JobDTO jobDTO = jobService.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Job not found", jobId)));

        PartnerDTO partnerDTO = partnerService.findByUserId(userService.getCurrentUserId());

        if (jobDTO.getStatusDTO().getId() != Estatus.activeStatus) {
            throw new ValidationExceptionV2(Collections.singletonMap("Job not active", Estatus.activeStatus));
        }

        if (partnerJobCareRepository.existsByPartnerIdAndJobId(partnerDTO.getId(), jobDTO.getId())) {
            throw new ConflictExceptionV2(
                    Collections.singletonMap(
                            "Job already exists with id: ", jobDTO.getId()
                    ));
        }

        PartnerJobCareDTO partnerJobCareDTO = new PartnerJobCareDTO();
        partnerJobCareDTO.setJobDTO(jobDTO);
        partnerJobCareDTO.setPartnerDTO(partnerDTO);

        PartnerJobCare care = partnerJobCareMapper.toEntity(partnerJobCareDTO);
        partnerJobCareRepository.save(care);

        return new MessageResponse(HttpServletResponse.SC_OK, "Job saved successfully", httpServletRequest.getServletPath());
    }

    @Override
    public MessageResponse deleteByJobIdAndPartnerId(Long jobId) {
        PartnerDTO partnerDTO = partnerService.findByUserId(userService.getCurrentUserId());
        if (partnerDTO == null) throw new ResourceNotFoundExceptionV2(
                Collections.singletonMap("Partner not found with id: ", userService.getCurrentUserId()));

        PartnerJobCare partnerJobCare = partnerJobCareRepository.findByJobIdAndPartnerId(jobId, partnerDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Job not found with id: ", jobId)));

        if (partnerDTO.getId() != partnerJobCare.getPartner().getId()) throw new AccessDeniedExceptionV2();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Job not found with id: ", jobId)));

        partnerJobCareRepository.delete(partnerJobCare);
        return new MessageResponse(HttpServletResponse.SC_OK, "Job deleted successfully", httpServletRequest.getServletPath());
    }

    @Override
    public boolean existsByJobIdAndPartnerId(Long jobId, Long partnerId) {
        return partnerJobCareRepository.existsByPartnerIdAndJobId(partnerId, jobId);
    }
}
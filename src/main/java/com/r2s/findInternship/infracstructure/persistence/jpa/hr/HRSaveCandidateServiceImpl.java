package com.r2s.findInternship.infracstructure.persistence.jpa.hr;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HrSaveCandidateDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.entity.HRSaveCandidate;
import com.r2s.findInternship.domain.repository.CandidateRepository;
import com.r2s.findInternship.domain.repository.HRSaveCandidateRepository;
import com.r2s.findInternship.domain.service.HRSaveCandidateService;
import com.r2s.findInternship.domain.service.HRService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.HRSaveCandidateMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HRSaveCandidateServiceImpl implements HRSaveCandidateService {
    private final HRSaveCandidateRepository hrSaveCandidateRepository;
    private final CandidateRepository candidateRepository;
    private final HRSaveCandidateMapper hrSaveCandidateMapper;
    private final HttpServletRequest request;
    private final UserService userService;
    private final HRService hrService;

    // Get
    @Override
    public PaginationDTO findAllByHrId(int no, int limit) {
        Long hrId = hrService.findByUserId(userService.getCurrentUserId()).getId();

        Page<Object> page = hrSaveCandidateRepository
                .findAllByHrId(hrId, PageRequest.of(no, limit))
                .map(hrSaveCandidateMapper::toDTO);

        return new PaginationDTO(
                page.getContent(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    @Override
    public HrSaveCandidateDTO findById(Long id) {
        HRSaveCandidate hrSaveCandidate = hrSaveCandidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Not found with id:", id)));

        if (hrSaveCandidate.getHr().getId() != userService.getCurrentUserId()) {
            throw new AccessDeniedExceptionV2();
        }

        return hrSaveCandidateMapper.toDTO(hrSaveCandidate);
    }

    // Post
    @Override
    public MessageResponse saveCandidate(Long candidateId) {
        HRDTO hrDTO = hrService.findByUserId(userService.getCurrentUserId());

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Candidate not found with id: ", candidateId)));

        if (candidate.getUser().getStatus() == null || candidate.getUser().getStatus().getId() != 1) {
            throw new ValidationExceptionV2(
                    Collections.singletonMap("Candidate has not activated the account with id: ", candidateId));
        }

        if (!candidate.isSearchable()) {
            throw new ValidationExceptionV2(Collections
                    .singletonMap("Candidate is not searchable and cannot be saved with id: ", candidateId));
        }

        if (hrSaveCandidateRepository.existsByCandidateIdAndHrId(candidateId, hrDTO.getId())) {
            throw new ValidationExceptionV2(Collections.singletonMap("Candidate is already saved with id:", candidateId));
        }

        HR hr = new HR();
        hr.setId(hrDTO.getId());

        HRSaveCandidate saved = hrSaveCandidateRepository.save(new HRSaveCandidate(null, candidate, hr));

        return new MessageResponse(HttpServletResponse.SC_OK, "Candidate saved successfully", request.getServletPath());
    }

    // Delete
    @Override
    @Transactional
    public MessageResponse deleteByCandidateIdAndHrId(Long candidateId) {
        Long hrId = hrService.findByUserId(userService.getCurrentUserId()).getId();

        if (!hrSaveCandidateRepository.existsByCandidateIdAndHrId(candidateId, hrId)) {
            throw new ResourceNotFoundExceptionV2(Collections.singletonMap("Candidate not found with id: ", candidateId));
        }

        hrSaveCandidateRepository.deleteByCandidateIdAndHrId(candidateId, hrId);
        return new MessageResponse(HttpServletResponse.SC_OK, "Candidate removed successfully", request.getServletPath());
    }

    @Override
    public boolean existsByCandidateIdAndHrId(Long candidateId, Long hrId) {
        return hrSaveCandidateRepository.existsByCandidateIdAndHrId(candidateId, hrId);
    }
}
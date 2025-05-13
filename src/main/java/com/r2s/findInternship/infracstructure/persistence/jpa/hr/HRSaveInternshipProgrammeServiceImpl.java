package com.r2s.findInternship.infracstructure.persistence.jpa.hr;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.entity.HRSaveInternshipProgramme;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.repository.HRSaveInternshipProgrammeRepository;
import com.r2s.findInternship.domain.repository.InternshipProgrammeRepository;
import com.r2s.findInternship.domain.service.HRSaveInternshipProgrammeService;
import com.r2s.findInternship.domain.service.HRService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.HRSaveInternshipProgrammeMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class HRSaveInternshipProgrammeServiceImpl implements HRSaveInternshipProgrammeService {
    private final HRSaveInternshipProgrammeRepository hrSaveInternshipProgrammeRepository;
    private final HRSaveInternshipProgrammeMapper hrSaveInternshipProgrammeMapper;
    private final InternshipProgrammeRepository internshipProgrammeRepository;
    private final HRService hrService;
    private final UserService userService;
    private final HttpServletRequest request;

    @Override
    public PaginationDTO findAllByHrId(int no, int limit) {
        Long hrId = hrService.findByUserId(userService.getCurrentUserId()).getId();

        Page<Object> page = hrSaveInternshipProgrammeRepository
                .findAllByHrId(hrId, PageRequest.of(no, limit))
                .map(hrSaveInternshipProgrammeMapper::toDTO);

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
    public MessageResponse saveInternshipProgramme(Long internshipProgrammeId) {
        HRDTO hrDTO = hrService.findByUserId(userService.getCurrentUserId());

        InternshipProgramme internshipProgramme = internshipProgrammeRepository.findById(internshipProgrammeId)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Internship Programme not found", internshipProgrammeId)
                ));

        if (hrSaveInternshipProgrammeRepository.existsByInternshipProgrammeIdAndHrId(internshipProgrammeId, hrDTO.getId())) {
            throw new IllegalStateException("Internship Programme is already saved.");
        }

        HR hr = new HR();
        hr.setId(hrDTO.getId());

        HRSaveInternshipProgramme saved = hrSaveInternshipProgrammeRepository
                .save(new HRSaveInternshipProgramme(null, internshipProgramme, hr));

        return new MessageResponse(
                HttpServletResponse.SC_OK,
                "Internship Programme saved successfully",
                request.getServletPath());
    }

    @Override
    @Transactional
    public MessageResponse deleteByInternshipProgrammeIdAndHrId(Long internshipProgrammeId) {
        Long hrId = hrService.findByUserId(userService.getCurrentUserId()).getId();

        if (!hrSaveInternshipProgrammeRepository.existsByInternshipProgrammeIdAndHrId(internshipProgrammeId, hrId)) {
            throw new ResourceNotFoundExceptionV2(
                    Collections.singletonMap("Internship Programme not found with id: ", internshipProgrammeId)
            );
        }

        hrSaveInternshipProgrammeRepository.deleteByInternshipProgrammeIdAndHrId(internshipProgrammeId, hrId);
        return new MessageResponse(HttpServletResponse.SC_OK, "Internship Programme removed successfully", request.getServletPath());
    }

    @Override
    public boolean existsByInternshipProgrammeIdAndHrId(Long internshipProgrammeId, Long hrId) {
        return hrSaveInternshipProgrammeRepository.existsByInternshipProgrammeIdAndHrId(internshipProgrammeId, hrId);
    }
}
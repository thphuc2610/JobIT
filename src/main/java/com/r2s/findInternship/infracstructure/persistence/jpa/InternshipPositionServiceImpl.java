package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.*;
import com.r2s.findInternship.domain.repository.InternshipPositionRepository;
import com.r2s.findInternship.domain.repository.PositionRepository;
import com.r2s.findInternship.domain.service.InternshipPositionService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InternshipPositionServiceImpl implements InternshipPositionService {
    private final InternshipPositionRepository internshipPositionRepository;
    private final PositionRepository positionRepository;

    @Override
    public boolean created(InternshipProgramme internshipProgramme, PositionDTO positionDTO) {
        if (positionDTO != null) {
            InternshipPosition internshipPosition = new InternshipPosition();
            internshipPosition.setInternshipProgramme(internshipProgramme);
            internshipPosition.setPosition(positionRepository.findById(positionDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Position not found with id: ", positionDTO.getId()))));
            internshipPositionRepository.save(internshipPosition);
            return true;
        }
        return false;
    }
}
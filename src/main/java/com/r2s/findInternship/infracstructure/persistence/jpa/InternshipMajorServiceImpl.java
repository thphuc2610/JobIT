package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.InternshipMajor;
import com.r2s.findInternship.domain.entity.InternshipProgramme;
import com.r2s.findInternship.domain.repository.InternshipMajorRepository;
import com.r2s.findInternship.domain.repository.MajorRepository;
import com.r2s.findInternship.domain.service.InternshipMajorService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InternshipMajorServiceImpl implements InternshipMajorService {
    private final InternshipMajorRepository internshipMajorRepository;
    private final MajorRepository majorRepository;
    @Override
    public boolean created(InternshipProgramme internshipProgramme, MajorDTO majorDTO) {
        if (majorDTO != null) {
            InternshipMajor internshipMajor = new InternshipMajor();
            internshipMajor.setInternshipProgramme(internshipProgramme);
            internshipMajor.setMajor(majorRepository.findById(majorDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                            Collections.singletonMap("Major not found with id: ", majorDTO.getId()))));
            internshipMajorRepository.save(internshipMajor);
            return true;
        }
        return false;
    }
}
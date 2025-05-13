package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.InternshipProgramme;

public interface InternshipMajorService {
    boolean created(InternshipProgramme internshipProgramme, MajorDTO majorDTO);
}
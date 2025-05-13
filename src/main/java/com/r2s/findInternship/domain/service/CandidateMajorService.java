package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.MajorDTO;

public interface CandidateMajorService {
    boolean update(long candidateId, List<MajorDTO> majorDTOs);
}
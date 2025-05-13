package com.r2s.findInternship.domain.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.candidate.CandidateFilterByHRDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.domain.common.MessageResponse;

public interface CandidateService {

    boolean isCurrentAuthor(Long id);

    @Transactional
    CandidateDTO create(CandidateCreationDTO candidateCreationDTO, MultipartFile fileAvatar);

    @Transactional
    CandidateDTO updatePersonalInfo(CandidateProfileDTO candidateProfileDTO, MultipartFile fileAvatar);

    @Transactional
    CandidateDTO updateJobInfo(CandidateProfileDTO candidateProfileDTO, MultipartFile fileCV);

    CandidateDTO findByUserId(long userId);

    CandidateDTO findById(long id);

    MessageResponse updateSearchable();

    MessageResponse updateMailReceive();

    PaginationDTO filterByHR(CandidateFilterByHRDTO candidateFilterByHRDTO, int no, int limit);
}
package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.candidate.CandidateSearchDTO;
import com.r2s.findInternship.application.dto.hr.HROtherInfoDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.hr.HRCreationDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HRProfileDTO;

public interface HRService {
    PaginationDTO findAll(int no, int limit);

    HRDTO findById(long id);

    HRDTO findByUserId(long userId);

    @Transactional
    HRDTO register(HRCreationDTO hrCreationDTO, MultipartFile fileAvatar);

    @Transactional
    HRDTO updateHRInfo(HRProfileDTO hrProfileDTO, MultipartFile fileAvatar);

    @Transactional
    HRDTO updateHRCompanyInfo(HROtherInfoDTO hrOtherInfoDTO, MultipartFile fileLogo);

    MessageResponse updateReceiveEmail();

    PaginationDTO searchCandidates(CandidateSearchDTO searchDTO, int no, int limit);
}
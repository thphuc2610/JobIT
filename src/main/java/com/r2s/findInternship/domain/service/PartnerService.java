package com.r2s.findInternship.domain.service;

import java.util.List;
import java.util.Optional;

import com.r2s.findInternship.application.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerDTO;
import com.r2s.findInternship.application.dto.partner.PartnerProfileDTO;
import com.r2s.findInternship.application.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.domain.entity.Partner;
import org.springframework.web.multipart.MultipartFile;

public interface PartnerService {
    long count();

    List<PartnerDTO> findAll();

    PaginationDTO findAll(int no, int limit);

    PaginationDTO findAllByUniversityId(Long universityId, int no, int limit);

    PartnerDTO create(PartnerCreationDTO partnerCreationDTO, MultipartFile fileAvatar);

    PartnerDTO updateUser(PartnerProfileDTO partnerProfileDTO,MultipartFile fileAvatar);

    List<UniversityPartnerDTOShow> findByUniversityId(Long universityId);

    PartnerDTO findByUserId(Long userId);

    PartnerDTO findById(Long id);

    PartnerDTO updateUser(PartnerCreationDTO partnerCreationDTO);

    PartnerCreationDTO readJson(String value);

    PaginationDTO filterInternship(InternshipProgrammeFilterDTO internshipProgrammeFilterDTO, int no, int limit);
}
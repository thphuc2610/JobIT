package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.domain.common.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.show.InternshipProgrammeDTOShow;

public interface InternshipProgrammeService {
	PaginationDTO findAllByNameLike(String name, int no, int limit);

	PaginationDTO findAllActiveByUniversityId(Long universityId, int no, int limit);

	InternshipProgrammeDTOShow findById(Long id);

	InternshipProgrammeDTO readJson(String content, MultipartFile file);

	List<InternshipProgrammeDTOShow> findAll();

	InternshipProgrammeDTOShow create(InternshipProgrammeDTO universityDemandDTO, MultipartFile file);

	InternshipProgrammeDTOShow update(InternshipProgrammeDTO universityDemandDTO, MultipartFile file, Long id);

	MessageResponse deleteById(Long id);

	InternshipProgrammeDTOShow updateStatus(Long id, boolean open);

	PaginationDTO findAllActive(int no, int limit);
}
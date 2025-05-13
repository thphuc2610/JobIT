package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.Major;

import java.util.List;

public interface MajorService {

	MajorDTO findById(Integer id);

	List<MajorDTO> findAll();

	MessageResponse create(MajorDTO majorDTO);

	MessageResponse deleteById(Integer id);
}
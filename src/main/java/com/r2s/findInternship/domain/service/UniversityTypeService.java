package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.UniversityTypeDTO;
import com.r2s.findInternship.domain.entity.UniversityType;

public interface UniversityTypeService {
	boolean existsById(Integer id);

	UniversityTypeDTO findById(Integer id);

	List<UniversityTypeDTO> findAll();

	UniversityTypeDTO create(UniversityType universityType);
}
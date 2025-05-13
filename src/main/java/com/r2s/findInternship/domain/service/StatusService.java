package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.StatusDTO;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.Status;

public interface StatusService {
	StatusDTO findById(int id);

	List<StatusDTO> findAll();

	StatusDTO findByName(String name);

	Status findByName(Estatus e);

	Status findByNameEntity(String name);
}
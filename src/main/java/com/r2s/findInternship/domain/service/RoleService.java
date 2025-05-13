package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.RoleDTO;

public interface RoleService {
	List<RoleDTO> findAll();

	RoleDTO findById(int id);

	RoleDTO findByName(String name);
}
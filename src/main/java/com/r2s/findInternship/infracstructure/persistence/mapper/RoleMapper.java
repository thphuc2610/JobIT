package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.r2s.findInternship.application.dto.RoleDTO;
import com.r2s.findInternship.domain.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	Role toEntity(RoleDTO roleDTO);

	RoleDTO toDTO(Role role);
}

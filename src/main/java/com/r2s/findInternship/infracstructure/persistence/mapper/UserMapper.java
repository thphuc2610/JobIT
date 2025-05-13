package com.r2s.findInternship.infracstructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.findInternship.application.dto.user.UserCreationDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.application.dto.user.UserProfileDTO;
import com.r2s.findInternship.domain.entity.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class, StatusMapper.class })
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	@Mapping(ignore = true, target = "role")
	@Mapping(ignore = true, target = "status")
	User toEntity(UserCreationDTO creationDTO);

	@Mapping(ignore = true, target = "role")
	@Mapping(ignore = true, target = "status")
	@Mapping(source = "userProfileDTO.mailReceive", target = "mailReceive")
	@Mapping(source = "userProfileDTO.city", target = "city")
	@Mapping(source = "userProfileDTO.district", target = "district")
	User toEntity(UserProfileDTO userProfileDTO);

	@Mapping(source = "role", target = "roleDTO")
	@Mapping(source = "status", target = "statusDTO")
	@Mapping(source = "mailReceive", target = "mailReceive")
	UserDTO toDTO(User user);

	@Mapping(source = "roleDTO", target = "role")
	@Mapping(source = "statusDTO", target = "status")
	@Mapping(source = "mailReceive", target = "mailReceive")
	User toEntity(UserDTO userDTO);
}
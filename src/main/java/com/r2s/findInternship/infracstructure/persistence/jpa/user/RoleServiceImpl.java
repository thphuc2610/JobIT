package com.r2s.findInternship.infracstructure.persistence.jpa.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.RoleDTO;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.entity.Role;
import com.r2s.findInternship.domain.repository.RoleRepository;
import com.r2s.findInternship.domain.service.RoleService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.RoleMapper;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDTO findById(int id) {
        return roleMapper.toDTO(
                roleRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(id))));
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(r -> roleMapper.toDTO(r)).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findByName(String name) {
        return roleMapper.toDTO(
                roleRepository.findByName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", name)));
    }

    @PostConstruct
    public void init() {
        List<Role> rolesToSave = new ArrayList<>();
        for (ERole eRole : ERole.values()) {
            if (!roleRepository.existsByName(eRole.toString())) {
                Role role = new Role();
                role.setName(eRole.toString());
                rolesToSave.add(role);
            }
        }

        if (!rolesToSave.isEmpty()) {
            roleRepository.saveAll(rolesToSave);
        }
    }
}
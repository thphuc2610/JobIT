package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.UniversityTypeDTO;
import com.r2s.findInternship.domain.entity.UniversityType;
import com.r2s.findInternship.domain.repository.UniversityTypeRepository;
import com.r2s.findInternship.domain.service.UniversityTypeService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.UniversityTypeMapper;

@Service
@RequiredArgsConstructor
public class UniversityTypeServiceImpl implements UniversityTypeService {
    private final UniversityTypeRepository universityTypeRepository;
    private final UniversityTypeMapper universityTypeMapper;

    @Override
    public UniversityTypeDTO create(UniversityType universityType) {
        return this.universityTypeMapper.toDTO(this.universityTypeRepository.save(universityType));
    }

    @Override
    public List<UniversityTypeDTO> findAll() {
        return universityTypeRepository.findAll().stream().map(item -> this.universityTypeMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public UniversityTypeDTO findById(Integer id) {
        UniversityType universityType = this.universityTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University type", "id", String.valueOf(id)));

        return this.universityTypeMapper.toDTO(universityType);
    }

    @Override
    public boolean existsById(Integer id) {
        return universityTypeRepository.existsById(id);
    }
}
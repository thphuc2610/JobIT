package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.MajorDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.Major;
import com.r2s.findInternship.domain.repository.MajorRepository;
import com.r2s.findInternship.domain.service.MajorService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.MajorMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService {
    private final MajorRepository majorRepository;
    private final MajorMapper majorMapper;

    @Override
    public List<MajorDTO> findAll() {
        return this.majorRepository.findAll().stream().map(item -> this.majorMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(MajorDTO majorDTO) {
        Major major = majorMapper.toEntity(majorDTO);
        if (majorRepository.existsByName(major.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", major.getName()));
        majorRepository.save(major);

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public MessageResponse deleteById(Integer id) {
        this.majorRepository.delete(this.majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public MajorDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
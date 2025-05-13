package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.Position;
import com.r2s.findInternship.domain.repository.CandidateApplicationRepository;
import com.r2s.findInternship.domain.repository.HRRepository;
import com.r2s.findInternship.domain.repository.JobRepository;
import com.r2s.findInternship.domain.repository.PositionRepository;
import com.r2s.findInternship.domain.service.PositionService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.PositionMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PossitionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final HRRepository hrRepository;
    private final JobRepository jobRepository;
    private final CandidateApplicationRepository candidateApplicationRepository;

    @Override
    public PositionDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<PositionDTO> findAll() {
        return this.positionRepository.findAll().stream().map(item -> this.positionMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(PositionDTO positionDTO) {
        Position position = positionMapper.toEntity(positionDTO);
        if (positionRepository.existsByName(position.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", position.getName()));
        positionRepository.save(position);

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public MessageResponse deleteById(Integer id) {
        this.positionRepository.delete(this.positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }
}
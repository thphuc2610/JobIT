package com.r2s.findInternship.infracstructure.persistence.jpa.candidate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.r2s.findInternship.domain.repository.PositionRepository;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidatePosition;
import com.r2s.findInternship.domain.entity.Position;
import com.r2s.findInternship.domain.repository.CandidatePositionRepository;
import com.r2s.findInternship.domain.service.CandidatePositionService;

@Service
@RequiredArgsConstructor
public class CandidatePositionServiceImpl implements CandidatePositionService {
    private final CandidatePositionRepository candidatePositionRepository;
    private final PositionRepository positionRepository;

    @Override
    public boolean update(long candidateId, List<PositionDTO> positionDTOs) {
        Queue<CandidatePosition> oldCandidatePositions = new LinkedList<>(
                candidatePositionRepository.findAllByCandidate_Id(candidateId));

        if (positionDTOs == null || positionDTOs.isEmpty()) {
            candidatePositionRepository.deleteAll(oldCandidatePositions);
            return true;
        }

        for (PositionDTO newPositionDTO : positionDTOs) {
            Position position = positionRepository.findById(newPositionDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

            if (oldCandidatePositions.isEmpty()) {
                CandidatePosition newCandidatePosition = new CandidatePosition();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);

                newCandidatePosition.setCandidate(candidate);
                newCandidatePosition.setPosition(position);
                candidatePositionRepository.save(newCandidatePosition);
            } else {
                CandidatePosition candidatePosition = oldCandidatePositions.poll();
                candidatePosition.setPosition(position);
                candidatePositionRepository.save(candidatePosition);
            }
        }

        while (!oldCandidatePositions.isEmpty()) {
            candidatePositionRepository.delete(oldCandidatePositions.poll());
        }

        return true;
    }
}
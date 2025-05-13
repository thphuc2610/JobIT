package com.r2s.findInternship.infracstructure.persistence.jpa.candidate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.r2s.findInternship.domain.repository.MajorRepository;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidateMajor;
import com.r2s.findInternship.domain.entity.Major;
import com.r2s.findInternship.domain.repository.CandidateMajorRepository;
import com.r2s.findInternship.domain.service.CandidateMajorService;

@Service
@RequiredArgsConstructor
public class CandidateMajorServiceImpl implements CandidateMajorService {
    private final CandidateMajorRepository candidateMajorRepository;
    private final MajorRepository majorRepository;

    @Override
    public boolean update(long candidateId, List<MajorDTO> majorDTOs) {
        Queue<CandidateMajor> oldCandidateMajors = new LinkedList<>(
                candidateMajorRepository.findAllByCandidate_Id(candidateId));

        if (majorDTOs == null || majorDTOs.isEmpty()) {
            candidateMajorRepository.deleteAll(oldCandidateMajors);
            return true;
        }

        for (MajorDTO newMajorDTO : majorDTOs) {
            Major major = majorRepository.findById(newMajorDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Major not found"));

            if (oldCandidateMajors.isEmpty()) {
                CandidateMajor newCandidateMajor = new CandidateMajor();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);

                newCandidateMajor.setCandidate(candidate);
                newCandidateMajor.setMajor(major);
                candidateMajorRepository.save(newCandidateMajor);
            } else {
                CandidateMajor candidateMajor = oldCandidateMajors.poll();
                candidateMajor.setMajor(major);
                candidateMajorRepository.save(candidateMajor);
            }
        }

        while (!oldCandidateMajors.isEmpty()) {
            candidateMajorRepository.delete(oldCandidateMajors.poll());
        }

        return true;
    }
}
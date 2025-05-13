package com.r2s.findInternship.domain.repository.specification;

import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.application.dto.candidate.CandidateSearchDTO;
import com.r2s.findInternship.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;

import java.util.ArrayList;
import java.util.List;

public class CandidateSpecification {

    public static Specification<Candidate> filterCandidates(CandidateSearchDTO searchDTO) {
        return (root, query, criteriaBuilder) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isTrue(root.get("searchable")));
            Join<Candidate, User> userJoin = root.join("user");
            predicates.add(criteriaBuilder.equal(userJoin.get("status").get("id"), 1));

            // Tìm kiếm theo công việc mong muốn
            if (searchDTO.getDesiredJob() != null && !searchDTO.getDesiredJob().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("desiredJob"), "%" + searchDTO.getDesiredJob() + "%"));
            }

            // Tìm kiếm theo khu vực làm việc mong muốn
            if (searchDTO.getDesiredWorkingProvince() != null && !searchDTO.getDesiredWorkingProvince().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("desiredWorkingProvince"), "%" + searchDTO.getDesiredWorkingProvince() + "%"));
            }

            // Tìm kiếm theo danh sách vị trí công việc
            if (searchDTO.getPositionIds() != null && !searchDTO.getPositionIds().isEmpty()) {
                Join<?, ?> positionJoin = root.join("candidatePositions").join("position");
                predicates.add(positionJoin.get("id").in(searchDTO.getPositionIds()));
            }

            // Tìm kiếm theo danh sách chuyên ngành
            if (searchDTO.getMajorIds() != null && !searchDTO.getMajorIds().isEmpty()) {
                Join<?, ?> majorJoin = root.join("candidateMajors").join("major");
                predicates.add(majorJoin.get("id").in(searchDTO.getMajorIds()));
            }

            // Tìm kiếm theo danh sách lịch trình làm việc
            if (searchDTO.getScheduleIds() != null && !searchDTO.getScheduleIds().isEmpty()) {
                Join<?, ?> scheduleJoin = root.join("candidateSchedules").join("schedule");
                predicates.add(scheduleJoin.get("id").in(searchDTO.getScheduleIds()));
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }
}
package com.r2s.findInternship.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.r2s.findInternship.application.dto.JobFilterDTO;
import com.r2s.findInternship.domain.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification implements Specification<Job> {
    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Specification<Job> filterJobs(JobFilterDTO jobFilterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo tiêu đề công việc (title)
            if (jobFilterDTO.getTitle() != null && !jobFilterDTO.getTitle().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + jobFilterDTO.getTitle().toLowerCase() + "%"));
            }

            // Lọc theo JobPosition
            if (jobFilterDTO.getJobPositionIds() != null && !jobFilterDTO.getJobPositionIds().isEmpty()) {
                Join<Job, JobPosition> jobPositionJoin = root.join("jobPositions");
                Join<JobPosition, Position> positionJoin = jobPositionJoin.join("position");
                predicates.add(positionJoin.get("id").in(jobFilterDTO.getJobPositionIds()));
            }

            // Lọc theo JobMajor
            if (jobFilterDTO.getJobMajorIds() != null && !jobFilterDTO.getJobMajorIds().isEmpty()) {
                Join<Job, JobMajor> jobMajorJoin = root.join("jobMajors");
                Join<JobMajor, Major> majorJoin = jobMajorJoin.join("major");
                predicates.add(majorJoin.get("id").in(jobFilterDTO.getJobMajorIds()));
            }

            // Lọc theo JobSchedule
            if (jobFilterDTO.getJobScheduleIds() != null && !jobFilterDTO.getJobScheduleIds().isEmpty()) {
                Join<Job, JobSchedule> jobScheduleJoin = root.join("jobSchedules");
                Join<JobSchedule, Schedule> scheduleJoin = jobScheduleJoin.join("schedule");
                predicates.add(scheduleJoin.get("id").in(jobFilterDTO.getJobScheduleIds()));
            }

            // Lọc theo địa chỉ
            if (jobFilterDTO.getAddress() != null && !jobFilterDTO.getAddress().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + jobFilterDTO.getAddress().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.UniversityType;

@Repository
public interface UniversityTypeRepository extends JpaRepository<UniversityType, Integer> {
}
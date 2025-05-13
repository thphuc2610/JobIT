package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    boolean existsByName(String name);

    boolean existsByShortName(String shortName);

    University findByName(String name);
}
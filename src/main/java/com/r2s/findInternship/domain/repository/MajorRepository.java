package com.r2s.findInternship.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    Optional<Major> findById(int id);

    boolean existsByName(String name);

    Major findByName(String name);
}
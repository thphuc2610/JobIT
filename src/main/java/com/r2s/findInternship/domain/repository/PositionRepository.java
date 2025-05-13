package com.r2s.findInternship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.findInternship.domain.entity.Position;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    boolean existsByName(String name);

    Position findByName(String name);

    Optional<Position> findById(Integer id);
}
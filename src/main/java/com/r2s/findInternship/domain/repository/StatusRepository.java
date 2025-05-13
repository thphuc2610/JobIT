package com.r2s.findInternship.domain.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	boolean existsByName(String name);

	Optional<Status> findByName(String name);
}
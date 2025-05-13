package com.r2s.findInternship.domain.repository;

import com.r2s.findInternship.domain.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Integer> {
    boolean existsById(String tokenId);
}
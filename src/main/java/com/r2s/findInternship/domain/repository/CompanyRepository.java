package com.r2s.findInternship.domain.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.r2s.findInternship.domain.entity.Company;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	Page<Company> findAllByNameLike(String name, Pageable pageable);

	Optional<Company> findByTax(String tax);

	List<Company> findAllByStatus_Name(String statusName);

	Page<Company> findAllByStatus_Name(String statusName, Pageable pageable);

	Long countByCreatedDateBetween(Date from, Date to);

	boolean existsByName(String name);

	boolean existsByTax(String tax);

	boolean existsByEmail(String email);

	boolean existsByWebsite(String website);

	boolean existsByIdNotAndName(long id, String name);

	boolean existsByIdNotAndEmail(long id, String email);

	boolean existsByIdNotAndWebsite(long id, String website);

	boolean existsByIdNotAndPhone(long id, String phone);

	boolean existsByIdNotAndTax(long id, String website);
}
package com.r2s.findInternship.domain.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.CompanyDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.entity.Company;

public interface CompanyService {
    PaginationDTO findAllByNameLike(String name, int no, int limit);

    PaginationDTO findAll(int no, int limit);

    PaginationDTO findAllActive(int no, int limit);

    @Transactional
    CompanyDTO create(CompanyDTO companyDTO, MultipartFile fileLogo);

    @Transactional
    CompanyDTO update(long id, CompanyDTO companyDTO, MultipartFile fileLogo);

    // Find by ID ---> Get by ID
    CompanyDTO findById(long id);

    Company getById(long id);

    boolean deleteById(long id);

    Long countByCreatedDate(Date from, Date to);
}
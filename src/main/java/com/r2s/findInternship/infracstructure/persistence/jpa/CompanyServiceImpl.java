package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.r2s.findInternship.application.dto.CompanyDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;

import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.Company;
import com.r2s.findInternship.domain.entity.HR;
import com.r2s.findInternship.domain.repository.CompanyRepository;
import com.r2s.findInternship.domain.repository.HRRepository;
import com.r2s.findInternship.domain.repository.StatusRepository;
import com.r2s.findInternship.domain.service.CompanyService;
import com.r2s.findInternship.domain.service.FileService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.CompanyMapper;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final CompanyMapper companyMapper;
    private final MessageSource messageSource;
    private final FileService fileService;
    private final HRRepository hrRepository;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    // Find by ID ---> Get by ID
    @Override
    public CompanyDTO findById(long id) {
        return this.companyMapper.toDTO(this.getById(id));
    }

    // Save
    @Override
    public CompanyDTO create(CompanyDTO companyDTO, MultipartFile fileLogo) {
        // check exists company info
        Map<String, Object> errors = new HashMap<>();
        if (companyRepository.existsByName(companyDTO.getName())) {
            errors.put("Name", messageSource.getMessage("error.companyNameExists", null, null));
        }
//        if (companyRepository.existsByEmail(companyDTO.getEmail())) {
//            errors.put("Email", messageSource.getMessage("error.companyEmailExists", null, null));
//        }
        if (companyRepository.existsByTax(companyDTO.getTax())) {
            errors.put("Tax", messageSource.getMessage("error.companyTaxExists", null, null));
        }
//        if (companyRepository.existsByWebsite(companyDTO.getName())) {
//            errors.put("Website", messageSource.getMessage("error.companyWebsiteExists", null, null));
//        }
        if (errors.size() > 0) {
            throw new ValidationExceptionV2(errors);
        }

        Company company = this.companyMapper.toEntity(companyDTO);
        company.setLogo(fileService.uploadFile(fileLogo));
        company.setLocation(null); // update
        company.setStatus(this.statusRepository.findByName(Estatus.Active.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Status", "name", Estatus.Active.toString())));

        return this.companyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDTO update(long id, CompanyDTO companyDTO, MultipartFile fileLogo) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        Company oldCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", String.valueOf(id)));

        if (hr.getCompany().getId() == id) {
            if (companyRepository.existsByIdNotAndEmail(oldCompany.getId(), companyDTO.getEmail())) {
                throw new IllegalArgumentException("BAD_REQUEST");
            }

            if (companyRepository.existsByIdNotAndWebsite(oldCompany.getId(), companyDTO.getWebsite())) {
                throw new IllegalArgumentException("BAD_REQUEST");
            }

            if (companyRepository.existsByIdNotAndPhone(oldCompany.getId(), companyDTO.getPhone())) {
                throw new IllegalArgumentException("BAD_REQUEST");
            }

            if (companyRepository.existsByIdNotAndTax(oldCompany.getId(), companyDTO.getTax())) {
                throw new IllegalArgumentException("BAD_REQUEST");
            }

            Company updateCompany = companyMapper.toEntity(companyDTO);
            if (oldCompany.getLogo() != null && !oldCompany.getLogo().isEmpty()) {
                fileService.deleteFile(oldCompany.getLogo());
            }

            updateCompany.setLogo(fileService.uploadFile(fileLogo));
            updateCompany.setId(oldCompany.getId());
            updateCompany.setStatus(oldCompany.getStatus());

            return companyMapper.toDTO(companyRepository.save(updateCompany));
        } else {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    // Get by ID
    @Override
    public Company getById(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", String.valueOf(id)));
    }

    // Find by name
    @Override
    public PaginationDTO findAllByNameLike(String name, int no, int limit) {
        Page<CompanyDTO> page = this.companyRepository.findAllByNameLike(name, PageRequest.of(no, limit))
                .map(c -> this.companyMapper.toDTO(c));

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        Page<CompanyDTO> page = this.companyRepository.findAll(PageRequest.of(no, limit))
                .map(c -> this.companyMapper.toDTO(c));

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public PaginationDTO findAllActive(int no, int limit) {
        Page<CompanyDTO> page = this.companyRepository
                .findAllByStatus_Name(Estatus.Active.toString(), PageRequest.of(no, limit))
                .map(c -> this.companyMapper.toDTO(c));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public boolean deleteById(long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", String.valueOf(id)));

        company.setStatus(statusRepository.findByName(Estatus.Delete.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Status", "name", Estatus.Delete.toString())));
        companyRepository.save(company);

        return true;
    }

    @Override
    public Long countByCreatedDate(Date from, Date to) {
        return companyRepository.countByCreatedDateBetween(from, to);
    }
}
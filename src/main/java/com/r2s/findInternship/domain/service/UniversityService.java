package com.r2s.findInternship.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;

import org.springframework.data.domain.Sort;

import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.University;
import org.springframework.web.multipart.MultipartFile;

public interface UniversityService {
    long count();

    boolean existsById(Long id);

    Optional<UniversityDTO> findById(Long id);

    List<UniversityDTO> findAll(Sort sort);

    List<UniversityDTO> findAllByNameLike(String name);

    List<UniversityDTO> findAllByShortNameLike(String shortName);

    List<UniversityDTO> findAll();

    PaginationDTO findAllByNameLike(String name, int no, int limit);

    PaginationDTO findAllByShortNameLike(String shortName, int no, int limit);

    University getById(Long id);

    List<UniversityDTO> findAllSort(String field);

    MessageResponse deleteById(int id);

    UniversityDTO create(UniversityDTO universityDTO, MultipartFile fileAvatar);

    void recoverById(Long id);

    List<Object[]> getNewStatistics();

    Long countByCreatedDate(LocalDateTime from, LocalDateTime to);

    List<Object[]> getStatusStatistics();

    PaginationDTO findAllByProvinceId(Long provinceId, int no, int limit);

    UniversityDTO changeStatus(Long id, UniversityDTO universityDTO);
}
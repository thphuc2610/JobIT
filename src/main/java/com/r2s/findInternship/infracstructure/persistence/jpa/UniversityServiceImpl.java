package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.repository.StatusRepository;
import com.r2s.findInternship.domain.service.*;

import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.UniversityMapper;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.University;
import com.r2s.findInternship.domain.repository.UniversityRepository;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;
    private final StatusRepository statusRepository;
    private final MessageSource messageSource;
    private final FileService fileService;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public List<UniversityDTO> findAll() {
        return this.universityRepository.findAll().stream().map(item -> this.universityMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public UniversityDTO create(UniversityDTO universityDTO, MultipartFile fileAvatar) {
        Map<String, Object> errors = new HashMap<>();
        if (universityRepository.existsByName(universityDTO.getName())) {
            errors.put("Name", messageSource.getMessage("error.UniversityExistsName", null, null));
        }
        if (universityRepository.existsByShortName(universityDTO.getShortName())) {
            errors.put("Short name", messageSource.getMessage("error.UniversityExistsShortName", null, null));
        }

        // Nếu có lỗi, ném ra ngoại lệ với thông tin lỗi
        if (errors.size() > 0) {
            throw new ValidationExceptionV2(errors);
        }

        // Chuyển DTO thành entity
        University university = this.universityMapper.toEntity(universityDTO);
        university.setAvatar(fileService.uploadFile(fileAvatar));
        university.setStatus(this.statusRepository.findByName(Estatus.Active.toString())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("Status not found: ", Estatus.Active.toString()))));
        // Lưu trường đại học vào cơ sở dữ liệu
        return this.universityMapper.toDTO(universityRepository.save(university));
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public boolean existsById(Long id) {
        return universityRepository.existsById(id);
    }


    @Override
    public Optional<UniversityDTO> findById(Long id) {
        return universityRepository.findById(id)
                .map(university -> Optional.of(universityMapper.toDTO(university)))
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("University not found with ID: ", id)
                ));
    }

    @Override
    public List<UniversityDTO> findAll(Sort sort) {
        return universityRepository.findAll(sort).stream()
                .map(universityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UniversityDTO> findAllByNameLike(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByNameLike'");
    }

    @Override
    public List<UniversityDTO> findAllByShortNameLike(String shortName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByShortNameLike'");
    }

    @Override
    public PaginationDTO findAllByNameLike(String name, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByNameLike'");
    }

    @Override
    public PaginationDTO findAllByShortNameLike(String shortName, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByShortNameLike'");
    }

    @Override
    public University getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<UniversityDTO> findAllSort(String field) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllSort'");
    }

    @Override
    public MessageResponse deleteById(int id) {
        this.universityRepository.delete(this.universityRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));
        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public void recoverById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recoverById'");
    }

    @Override
    public List<Object[]> getNewStatistics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNewStatistics'");
    }

    @Override
    public Long countByCreatedDate(LocalDateTime from, LocalDateTime to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByCreatedDate'");
    }

    @Override
    public List<Object[]> getStatusStatistics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatusStatistics'");
    }

    @Override
    public PaginationDTO findAllByProvinceId(Long provinceId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByProvinceId'");
    }

    @Override
    public UniversityDTO changeStatus(Long id, UniversityDTO universityDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeStatus'");
    }
}
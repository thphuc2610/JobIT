package com.r2s.findInternship.infracstructure.persistence.jpa.hr;

import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateSearchDTO;
import com.r2s.findInternship.application.dto.hr.HROtherInfoDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.*;
import com.r2s.findInternship.domain.repository.*;
import com.r2s.findInternship.domain.service.FileService;
import com.r2s.findInternship.domain.repository.specification.CandidateSpecification;
import com.r2s.findInternship.infracstructure.exception.exception_v2.AccessDeniedExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import com.r2s.findInternship.infracstructure.persistence.mapper.CandidateMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.hr.HRCreationDTO;
import com.r2s.findInternship.application.dto.hr.HRDTO;
import com.r2s.findInternship.application.dto.hr.HRProfileDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.service.CompanyService;
import com.r2s.findInternship.domain.service.HRService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.persistence.mapper.HRMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HRServiceImpl implements HRService {
    private final HRMapper hrMapper;
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final HRRepository hrRepository;
    private final MessageSource messageSource;
    private final HttpServletRequest request;
    private final FileService fileService;
    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    // Get All
    @Override
    public PaginationDTO findAll(int no, int limit) {
        Page<HRDTO> page = hrRepository.findAll(PageRequest.of(no, limit)).map(hr -> hrMapper.toDTO(hr));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    // Get one by id
    @Override
    public HRDTO findById(long id) {
        return hrMapper.toDTO(
                hrRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                Collections.singletonMap("Not found with hr id", id))));
    }

    // Get by user id
    @Override
    public HRDTO findByUserId(long userId) {
        return hrMapper.toDTO(
                hrRepository.findByUser_Id(userId)
                        .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                                Collections.singletonMap("Not found with user id", userId))));
    }

    // Sign up
    @Override
    public HRDTO register(HRCreationDTO hrCreationDTO, MultipartFile fileAvatar) {
        UserDTO createdUserDTO = userService.create(hrCreationDTO.getUserCreationDTO(), fileAvatar, ERole.HR);

        // check confirm password
        if (!hrCreationDTO.getUserCreationDTO().getPassword().equals(hrCreationDTO.getUserCreationDTO().getConfirmPassword())) {
            throw new ValidationExceptionV2(
                    Collections.singletonMap("confirmPassword",
                            messageSource.getMessage("error.passwordMatch", null, Locale.getDefault()))
            );
        }

        // if not exists hr's company when hr register, create new company first
        if (hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO().getId() == null) {
            hrCreationDTO.getHrOtherInfoDTO()
                    .setCompanyDTO(companyService.create(hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO(), null));
        } else {
            hrCreationDTO.getHrOtherInfoDTO()
                    .setCompanyDTO(companyService.findById(hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO().getId()));
        }

        HR hr = hrMapper.toEntity(hrCreationDTO);
        hr.getUser().setId(createdUserDTO.getId());

        HRDTO hrDTO = hrMapper.toDTO(hrRepository.save(hr));
        hrDTO.setUserDTO(createdUserDTO);
        return hrDTO;
    }

    // Update information HR
    @Override
    public HRDTO updateHRInfo(HRProfileDTO hrProfileDTO, MultipartFile fileAvatar) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(AccessDeniedExceptionV2::new);

        UserDTO updatedUserDTO = userService.update(
                hr.getUser().getId(), hrProfileDTO.getUserProfileDTO(), fileAvatar);

        HR updateHR = hrMapper.toEntity(hrProfileDTO);
        updateHR.setId(hr.getId());
        updateHR.getUser().setId(updatedUserDTO.getId());
        updateHR.setCompany(hr.getCompany());

        HRDTO updatedHRDTO = hrMapper.toDTO(hrRepository.save(updateHR));

        return updatedHRDTO;
    }

    // Update information company
    @Transactional
    @Override
    public HRDTO updateHRCompanyInfo(HROtherInfoDTO hrOtherInfoDTO, MultipartFile fileLogo) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HR hr = hrRepository.findByUsername(username).orElseThrow(AccessDeniedExceptionV2::new);

        Company company = companyRepository.findById(hr.getCompany().getId())
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(
                        Collections.singletonMap("Company not found with id: ", hr.getCompany().getId())));

        // Cập nhật logo nếu có
        if (fileLogo != null && !fileLogo.isEmpty()) {
            if (company.getLogo() != null) {
                fileService.deleteFile(company.getLogo());
            }
            String logo = fileService.uploadFile(fileLogo);
            company.setLogo(logo);
        }

        company.setEmail(hrOtherInfoDTO.getCompanyDTO().getEmail());
        company.setPhone(hrOtherInfoDTO.getCompanyDTO().getPhone());
        company.setPersonnelSize(hrOtherInfoDTO.getCompanyDTO().getPersonnelSize());
        company.setWebsite(hrOtherInfoDTO.getCompanyDTO().getWebsite());
        company.setLocation(hrOtherInfoDTO.getCompanyDTO().getLocation());
        company.setCountry(hrOtherInfoDTO.getCompanyDTO().getCountry());
        company.setDistrict(hrOtherInfoDTO.getCompanyDTO().getDistrict());
        company.setProvince(hrOtherInfoDTO.getCompanyDTO().getProvince());
        company.setDescription(hrOtherInfoDTO.getCompanyDTO().getDescription());

        companyRepository.save(company);

        return hrMapper.toDTO(hr);
    }

    // Receive Email
    @Override
    public MessageResponse updateReceiveEmail() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = hrRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("username not found", username)));

        User user = hr.getUser();
        if (user == null) {
            throw new ResourceNotFoundExceptionV2(Collections.singletonMap("User not found for HR: ", username));
        }

        user.setMailReceive(!user.isMailReceive());
        userRepository.save(user);

        return new MessageResponse(HttpServletResponse.SC_OK, "Successful", request.getServletPath());
    }

    // Search candidates
    @Override
    public PaginationDTO searchCandidates(CandidateSearchDTO searchDTO, int no, int limit) {
        Specification<Candidate> spec = CandidateSpecification.filterCandidates(searchDTO);
        Page<Candidate> candidatePage = candidateRepository.findAll(spec, PageRequest.of(no, limit));

        List<CandidateDTO> candidateDTOs = candidatePage.getContent()
                .stream()
                .map(candidateMapper::toDTO)
                .collect(Collectors.toList());

        return new PaginationDTO(
                candidateDTOs,
                candidatePage.isFirst(),
                candidatePage.isLast(),
                candidatePage.getTotalPages(),
                candidatePage.getTotalElements(),
                candidatePage.getTotalPages(),
                candidatePage.getSize()
        );
    }
}
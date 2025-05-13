package com.r2s.findInternship.presentation.controller.partner;

import com.r2s.findInternship.application.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.application.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.application.dto.partner.PartnerProfileDTO;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.JsonReaderService;
import com.r2s.findInternship.domain.service.PartnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping(ApiURL.PARTNER)
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;
    private final JsonReaderService<Object> jsonReaderService;

    // Create User Partner
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody PartnerCreationDTO partnerCreationDTO, @RequestBody MultipartFile fileAvatar) {
        return new ResponseEntity<>(partnerService.create(partnerCreationDTO, fileAvatar), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasAuthority('Role_Partner')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@Valid @RequestPart(name = "partnerProfileDTO") String partnerProfileDTOJson,
                                    @Valid @RequestPart(name = "fileAvatar", required = false) MultipartFile fileAvatar) {

        // Transfer from String ( JSON ) to PartnerDTO
        PartnerProfileDTO partnerProfileDTO = jsonReaderService.readValue(
                partnerProfileDTOJson, PartnerProfileDTO.class);
        return ResponseEntity.ok(partnerService.updateUser(partnerProfileDTO, fileAvatar));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Partner') || hasAuthority('Role_HR')")
    @GetMapping("/search")
    public ResponseEntity<?> filter(@RequestParam int no,
                                    @RequestParam int limit,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String location,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                    @RequestParam(required = false) Integer statusId) {

        InternshipProgrammeFilterDTO filterDTO = new InternshipProgrammeFilterDTO(title, location, startDate, endDate, statusId);
        return ResponseEntity.ok(partnerService.filterInternship(filterDTO, no, limit));
    }
}
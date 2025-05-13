package com.r2s.findInternship.presentation.controller.admin.general;

// import com.r2s.findInternship.constant.ApiURL;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.data.dto.HRApplicationDTO;
// import com.r2s.findInternship.service.HRApplicationService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @SecurityRequirement(name = "Bearer Authentication")
// @RequestMapping(ApiURL.ADMIN_HR_APPLICATION_GENERAL)
// @PreAuthorize("hasAuthority('Role_HR')")

// public class AdminHRApplicationGeneralController {
// @Autowired
// private HRApplicationService hrApplicationService;

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody HRApplicationDTO
// hrApplicationDTO) {
// return new ResponseEntity<>(hrApplicationService.create(hrApplicationDTO),
// HttpStatus.CREATED);
// }
// }

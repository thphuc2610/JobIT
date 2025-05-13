package com.r2s.findInternship.presentation.controller.admin;

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

// import com.r2s.findInternship.data.dto.PositionDTO;
// import com.r2s.findInternship.service.JobPositionService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_JOB_POSITION)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminJobPositionController {
// @Autowired
// private JobPositionService jobPositionService;

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody PositionDTO jobPositionDTO) {
// return new ResponseEntity<>(jobPositionService.create(jobPositionDTO),
// HttpStatus.CREATED);
// }
// }

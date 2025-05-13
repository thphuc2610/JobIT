package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.data.dto.StatusDTO;
// import com.r2s.findInternship.service.StatusService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_STATUS)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminStatusController {
// @Autowired
// private StatusService statusService;

// @GetMapping("/{id}")
// public ResponseEntity<?> getById(@PathVariable int id) {
// return ResponseEntity.ok(this.statusService.findById(id));
// }

// @GetMapping("")
// public ResponseEntity<?> getAll() {
// return ResponseEntity.ok(this.statusService.findAll());
// }

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody StatusDTO statusDTO) {
// return new ResponseEntity<>(this.statusService.create(statusDTO),
// HttpStatus.CREATED);
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@RequestBody StatusDTO statusDTO,
// @PathVariable int id) {
// return ResponseEntity.ok(this.statusService.update(id, statusDTO));
// }

// }

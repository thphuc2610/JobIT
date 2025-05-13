package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import com.r2s.findInternship.data.dto.MajorDTO;
// import com.r2s.findInternship.service.MajorService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_MAJOR)
// public class AdminMajorController {
// @Autowired
// private MajorService majorService;

// @GetMapping("")
// public ResponseEntity<?> getAll(@RequestParam(defaultValue = PageDefault.NO)
// int no, @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.majorService.findAll(no, limit));
// }

// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.majorService.findById(id));
// }

// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody MajorDTO majorDTO) {
// return new ResponseEntity<>(this.majorService.create(majorDTO),
// HttpStatus.CREATED);
// }

// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// @PutMapping("/{id}")
// public ResponseEntity<?> update(@PathVariable int id, @RequestBody MajorDTO
// majorDTO) {
// return ResponseEntity.ok(this.majorService.update(id, majorDTO));
// }

// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// this.majorService.deleteById(id);
// return ResponseEntity.ok("DELETED");
// }
// }

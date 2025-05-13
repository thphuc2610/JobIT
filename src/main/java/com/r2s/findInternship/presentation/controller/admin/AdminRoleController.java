package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.data.entity.Role;
// import com.r2s.findInternship.service.RoleService;

// @RestController
// @RequestMapping(ApiURL.ADMIN_ROLE)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminRoleController {
// @Autowired
// private RoleService roleService;

// @GetMapping("")
// public ResponseEntity<?> getAll() {
// return ResponseEntity.ok(this.roleService.findAll());
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> getById(@PathVariable int id) {
// return ResponseEntity.ok(this.roleService.findById(id));
// }

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody Role role) {
// return new ResponseEntity<>(this.roleService.create(role),
// HttpStatus.CREATED);
// }
// }

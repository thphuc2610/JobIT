package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
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
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.r2s.findInternship.data.dto.CandidateApplicationDTO;
// import com.r2s.findInternship.service.CandidateApplicationService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController

// @RequestMapping(ApiURL.ADMIN_APPLICATION)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminApplicationController {
// @Autowired
// private CandidateApplicationService applicationService;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.applicationService.findAll(no, limit));
// }

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> create(@RequestPart("application") String
// applicationJson,
// @RequestPart(name = "fileCV", required = false) MultipartFile fileCV) {
// CandidateApplicationDTO applicationDTO =
// applicationService.readJson(applicationJson, fileCV);
// return new ResponseEntity<>(this.applicationService.create(applicationDTO),
// HttpStatus.CREATED);
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.applicationService.findById(id));
// }

// @GetMapping("/job/{id}")
// public ResponseEntity<?> findAllByJobId(@PathVariable int id,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.applicationService.findAllByJobId(id, no,
// limit));
// }

// @GetMapping("/candidate/{id}")
// public ResponseEntity<?> findAllByCandidateId(@PathVariable int id,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.applicationService.findAllByCandidateId(id, no,
// limit));
// }

// @GetMapping("/candidate/user/{username}")
// public ResponseEntity<?> findAllByUsername(@PathVariable String username,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.applicationService.findAllByUsername(username,
// no, limit));
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@PathVariable int id, @RequestBody
// CandidateApplicationDTO applicationDTO) {
// return ResponseEntity.ok(this.applicationService.update(id, applicationDTO));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// this.applicationService.deleteById(id);
// return ResponseEntity.ok("DELETED");
// }

// }

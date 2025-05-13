package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.common.MessageResponse;
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

// import com.r2s.findInternship.data.dto.InternshipProgrammeDTO;
// import com.r2s.findInternship.data.dto.show.UniversityDemandDTOShow;
// import com.r2s.findInternship.service.UniversityDemandService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @RequestMapping(ApiURL.ADMIN_UNIVERSITY_DEMAND)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAnyAuthority('Role_Partner','Role_Admin')")
// public class AdminUniversityDemandController {
// @Autowired
// private UniversityDemandService universityDemandService;

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE }) // ADMIN
// public ResponseEntity<?> create(@RequestPart("demand") String
// candidateCreateDTOJson,
// @RequestPart(name = "fileSV", required = false) MultipartFile file) {
// InternshipProgrammeDTO dto =
// this.universityDemandService.readJson(candidateCreateDTOJson, file);//
// Tranfer
// // LocalDateTime
// // to Object
// return new
// ResponseEntity<UniversityDemandDTOShow>(this.universityDemandService.create(dto),
// HttpStatus.CREATED);
// }

// @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> update(@PathVariable int id, @RequestPart("demand")
// String candidateCreateDTOJson,
// @RequestPart(name = "fileSV", required = false) MultipartFile file) {
// InternshipProgrammeDTO dto =
// this.universityDemandService.readJson(candidateCreateDTOJson, file);//
// Tranfer
// // LocalDateTime
// // to Object
// return ResponseEntity.ok(this.universityDemandService.update(dto, id));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// this.universityDemandService.deleteById(id);
// return ResponseEntity.ok(new MessageResponse(200, "Demand University
// deleted!", null));
// }

// @GetMapping("/search")
// public ResponseEntity<?> searchByName(@RequestParam String name,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.universityDemandService.findAllByNameLike(name,
// no, limit));
// }

// @PutMapping("/status")
// public ResponseEntity<?> updateStatus(@RequestBody InternshipProgrammeDTO
// universityDemandDTO) {
// return
// ResponseEntity.ok(this.universityDemandService.updateStatus(universityDemandDTO));
// }

// }

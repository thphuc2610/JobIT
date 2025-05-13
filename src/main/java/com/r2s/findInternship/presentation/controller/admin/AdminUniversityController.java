package com.r2s.findInternship.presentation.controller.admin;

// import java.util.Map;

// import com.r2s.findInternship.constant.ApiURL;
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

// import com.r2s.findInternship.data.dto.UniversityCreationDTO;
// import com.r2s.findInternship.data.dto.UniversityDTO;
// import com.r2s.findInternship.service.UniversityService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_UNIVERSITY)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminUniversityController {

// @Autowired
// private UniversityService universityService;

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> create(@RequestPart("university") String
// universityJson,
// @RequestPart(name = "logo", required = false) MultipartFile file) {
// UniversityCreationDTO dto = this.universityService.readJson(universityJson,
// "", null, file);
// return new ResponseEntity<UniversityDTO>(this.universityService.create(dto),
// HttpStatus.CREATED);
// }

// @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> update(@PathVariable int id,
// @RequestPart(name = "university", required = false) String universityJson,
// @RequestPart(name = "file", required = false) MultipartFile file) {
// UniversityCreationDTO universityDTO =
// this.universityService.readJson(universityJson, "", null, file);
// return ResponseEntity.ok(this.universityService.update(universityDTO, id));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// return ResponseEntity.ok(this.universityService.deleteById(id));
// }

// @GetMapping("/re/{id}")
// public ResponseEntity<?> recoverById(@PathVariable int id) {
// this.universityService.recoverById(id);
// return ResponseEntity.ok("RECOVER IS SUCCESSFUL");
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.universityService.findById(id));
// }

// @GetMapping("/search/{name}")
// public ResponseEntity<?> getUniversityByNameAndPaging(@PathVariable String
// name,
// @RequestParam(required = false) Map<String, String> params) {
// int no = Integer.parseInt(params.getOrDefault("no", "0"));
// int limit = Integer.parseInt(params.getOrDefault("limit", "20"));
// return ResponseEntity.ok(universityService.findAllByNameLike(name, no,
// limit));
// }

// @PutMapping("/status/{id}")
// public ResponseEntity<?> changeStatus(@RequestBody UniversityDTO
// universityDTO, @PathVariable int id) {
// return ResponseEntity.ok(this.universityService.changeStatus(id,
// universityDTO));
// }
// }

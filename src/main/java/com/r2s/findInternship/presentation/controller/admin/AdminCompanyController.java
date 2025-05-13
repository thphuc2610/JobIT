package com.r2s.findInternship.presentation.controller.admin;

// import java.time.LocalDateTime;

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
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.r2s.findInternship.data.dto.CompanyDTO;
// import com.r2s.findInternship.data.dto.CompanyLocationDTO;
// import com.r2s.findInternship.data.dto.LocationDTO;
// import com.r2s.findInternship.service.CompanyLocationService;
// import com.r2s.findInternship.service.CompanyService;
// import com.r2s.findInternship.service.LocationService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_COMPANY)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminCompanyController {

// @Autowired
// private CompanyService companyService;
// @Autowired
// private LocationService locationService;
// @Autowired
// private CompanyLocationService companyLocationService;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no, @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(companyService.findAll(no, limit));
// }

// @GetMapping("/{name}")
// public ResponseEntity<?> findAllByNameLike(@PathVariable String name,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.companyService.findAllByNameLike(name, no,
// limit));
// }

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> create(@RequestPart("company") String companyJson,
// @RequestPart("location") String locationJson,
// @RequestPart(name = "fileLogo", required = false) MultipartFile fileLogo) {
// // location
// LocationDTO locationDTO = locationService.readJson(locationJson);
// locationDTO = locationService.create(locationDTO);
// // company
// CompanyDTO companyDTO = this.companyService.readJson(companyJson, fileLogo);
// companyService.checkCompany(companyDTO); // Kiểm tra trùng
// companyDTO = companyService.create(companyDTO);
// // location of company
// CompanyLocationDTO companyLocationDTO = new CompanyLocationDTO();
// companyLocationDTO.setCompanyDTO(companyDTO);
// companyLocationDTO.setLocationDTO(locationDTO);

// companyLocationService.create(companyLocationDTO);
// return new ResponseEntity<CompanyDTO>(companyDTO, HttpStatus.CREATED);
// }

// @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> update(@PathVariable int id, @RequestPart("company")
// String companyJson,
// @RequestPart(name = "fileLogo", required = false) MultipartFile fileLogo) {
// // company
// CompanyDTO companyDTO = this.companyService.readJson(companyJson, fileLogo);
// companyService.checkCompany(id, companyDTO); // Kiểm tra trùng
// return ResponseEntity.ok(this.companyService.update(id, companyDTO));
// }

// @GetMapping("/{id}")
// public ResponseEntity<CompanyDTO> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.companyService.findById(id));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<String> deleteById(@PathVariable int id) {
// this.companyService.deleteById(id);
// return ResponseEntity.ok("DELETED");
// }

// @PreAuthorize("hasRole('Admin')")
// @GetMapping("/count")
// public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from,
// @RequestParam LocalDateTime to) {
// return ResponseEntity.ok(this.companyService.countByCreatedDate(from, to));
// }

// @PreAuthorize("hasRole('Admin')")
// @GetMapping("/statistics/new")
// public ResponseEntity<?> getNewStatistics() {
// return ResponseEntity.ok(this.companyService.getNewStatistics());
// }

// @PreAuthorize("hasRole('Admin')")
// @GetMapping("/statistics/status")
// public ResponseEntity<?> getStatusStatistics() {
// return ResponseEntity.ok(this.companyService.getStatusStatistics());
// }

// }

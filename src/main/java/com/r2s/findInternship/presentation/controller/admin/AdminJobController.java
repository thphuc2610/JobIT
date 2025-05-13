package com.r2s.findInternship.presentation.controller.admin;

// import java.time.LocalDateTime;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.data.dto.JobCreationDTO;
// import com.r2s.findInternship.data.dto.JobDTO;
// import com.r2s.findInternship.data.dto.LocationDTO;
// import com.r2s.findInternship.service.JobService;
// import com.r2s.findInternship.service.LocationService;

// @RestController
// @RequestMapping(ApiURL.ADMIN_JOB)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminJobController {
// @Autowired
// private JobService jobService;
// @Autowired
// private LocationService locationService;

// @GetMapping("")
// public ResponseEntity<?> findAllActive(@RequestParam(defaultValue =
// PageDefault.NO) int no, @RequestParam(defaultValue = PageDefault.LIMIT) int
// limit) {
// return ResponseEntity.ok(jobService.findAllActive(no, limit));
// }

// @GetMapping("/user/{id}")
// public ResponseEntity<?> findAllActiveByUserId(@PathVariable("id") long
// userId, @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.jobService.findAllActiveByUserId(userId, no,
// limit));
// }

// @GetMapping("/hr/{id}")
// public ResponseEntity<?> findAllActiveByHrId(@PathVariable("id") int hrId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.jobService.findAllActiveByHrId(hrId, no,
// limit));
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.jobService.findById(id));
// }

// @GetMapping("/user/{username}")
// public ResponseEntity<?> findAllActiveByUsername(@PathVariable String
// username, @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.jobService.findAllActiveByUsername(username,
// no, limit));
// }

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody JobCreationDTO jobCreationDTO) {
// // LocationDTO locationDTO = jobDTO.getLocationDTO();
// // locationDTO = locationService.create(locationDTO);
// // jobDTO.setLocationDTO(locationDTO);
// // return new ResponseEntity<>(this.jobService.create(jobDTO),
// HttpStatus.CREATED);
// return null;
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@PathVariable int id, @RequestBody JobDTO
// jobDTO) {
// // LocationDTO locationDTO = jobDTO.getLocationDTO();
// // locationDTO = locationService.update(locationDTO.getId(), locationDTO);
// // jobDTO.setLocationDTO(locationDTO);
// // return ResponseEntity.ok(this.jobService.update(id, jobDTO));
// return null;
// }

// // @PutMapping("/status/{id}")
// // public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody
// JobDTO jobDTO) {
// // return ResponseEntity.ok(this.jobService.changeStatus(id, jobDTO));
// // }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// this.jobService.deleteById(id);
// return ResponseEntity.ok("DELETED");
// }

// @GetMapping("statistics/newJob")
// public ResponseEntity<?> getNewStatistics() {
// return ResponseEntity.ok(this.jobService.getNewStatistics());
// }

// @GetMapping("statistics/countAll")
// public ResponseEntity<?> getCountAll() {
// return ResponseEntity.ok(this.jobService.count());
// }

// @GetMapping("/count")
// public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from,
// @RequestParam LocalDateTime to) {
// return ResponseEntity.ok(this.jobService.countByCreatedDate(from, to));
// }

// @GetMapping("statistics/status")
// public ResponseEntity<?> getStatusStatistics() {
// return ResponseEntity.ok(this.jobService.getStatusStatistics());
// }

// @GetMapping("company/{id}")
// public ResponseEntity<?> findAllActiveByCompanyId(@PathVariable int id,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return
// ResponseEntity.ok(this.jobService.findAllActiveByCompanyIdShowForHr(id, no,
// limit));
// }

// }

package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import com.r2s.findInternship.data.dto.CandidateDTO;
// import com.r2s.findInternship.service.CandidateService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @RequestMapping(ApiURL.ADMIN_CANDIDATE)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminCandidateController {
// @Autowired
// private CandidateService candidateService;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.candidateService.findAll(no, limit));
// }

// @GetMapping("/{id}")
// public ResponseEntity<CandidateDTO> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.candidateService.findById(id));
// }

// @GetMapping("/job/{id}")
// public ResponseEntity<?> findByJobId(@PathVariable("id") int jobId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.candidateService.findByJobId(jobId, no,
// limit));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// candidateService.deleteById(id);
// return ResponseEntity.ok("Deleted");
// }

// @GetMapping("/major/{id}")
// public ResponseEntity<?> findAllByMajor(@PathVariable("id") int majorId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.candidateService.findAllByMajorId(majorId, no,
// limit));
// }

// @GetMapping("/u/{username}")
// public ResponseEntity<?> getCandidateByUsername(@PathVariable String
// username) {
// return ResponseEntity.ok(this.candidateService.findByUsername(username));
// }
// }

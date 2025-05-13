package com.r2s.findInternship.presentation.controller.admin;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;
// import com.r2s.findInternship.service.RateService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(ApiURL.ADMIN_RATE)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminRateController {
// @Autowired
// private RateService rateService;

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// return ResponseEntity.ok(this.rateService.deleteById(id));
// }

// @GetMapping("re/{id}")
// public ResponseEntity<?> recoverById(@PathVariable int id) {
// return ResponseEntity.ok(this.rateService.recoverById(id));
// }

// @GetMapping("/company/{id}")
// public ResponseEntity<?> findAllByCompanyId(@PathVariable("id") int
// companyId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(rateService.findAllByCompanyId(companyId, no,
// limit));
// }
// }

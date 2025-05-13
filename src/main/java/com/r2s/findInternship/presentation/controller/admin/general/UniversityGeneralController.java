package com.r2s.findInternship.presentation.controller.admin.general;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.service.UniversityService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @RequestMapping(ApiURL.UNIVERSITY_GENERAL)
// public class UniversityGeneralController {
// @Autowired
// private UniversityService universityService;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.universityService.findAll(no, limit));
// }

// @GetMapping("get-all/{field}")
// public ResponseEntity<?> findAllSort(@PathVariable String field) {
// return ResponseEntity.ok(this.universityService.findAllSort(field));
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.universityService.findById(id));
// }

// @GetMapping("/{name}")
// public ResponseEntity<?> findAllByNameLike(@PathVariable String name,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.universityService.findAllByNameLike(name, no,
// limit));
// }

// @GetMapping("/{shortname}")
// public ResponseEntity<?> findAllByShortNameLike(@PathVariable String
// shortName,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return
// ResponseEntity.ok(this.universityService.findAllByShortNameLike(shortName,
// no, limit));
// }

// @GetMapping("/province/{id}")
// public ResponseEntity<?> findAllByProvinceId(@PathVariable("id") int
// provinceId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return
// ResponseEntity.ok(this.universityService.findAllByProvinceId(provinceId, no,
// limit));
// }

// }
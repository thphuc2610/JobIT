package com.r2s.findInternship.presentation.controller.admin.general;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;
// import com.r2s.findInternship.data.dto.DemandFilterDTO;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.service.UniversityDemandService;

// @RestController
// @RequestMapping(ApiURL.UNIVERSITY_DEMAND_GENERAL)
// public class UniversityDemandGeneralController {

// @Autowired
// private UniversityDemandService universityDemandService;

// @GetMapping("")
// public ResponseEntity<?> getAll(
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit,
// @RequestParam(required = false) String name,
// @RequestParam(required = false) List<String> universityTypeIds,
// @RequestParam(required = false) List<String> majorIds,
// @RequestParam(required = false) Integer universityId,
// @RequestParam(required = false) String order) {

// DemandFilterDTO demandFilterDTO = new DemandFilterDTO(name,
// universityTypeIds, majorIds, universityId, order);
// return ResponseEntity.ok(this.universityDemandService.filter(demandFilterDTO,
// no, limit));

// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.universityDemandService.findById(id));
// }

// @GetMapping("active//university/{id}")
// public ResponseEntity<?> findAllActiveByUniversityId(@PathVariable("id") int
// universityId,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return
// ResponseEntity.ok(this.universityDemandService.findAllActiveByUniversityId(universityId,
// no, limit));
// }

// }

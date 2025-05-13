package com.r2s.findInternship.presentation.controller.admin.general;

// import com.r2s.findInternship.constant.ApiURL;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.data.dto.PositionDTO;
// import com.r2s.findInternship.data.entity.Position;
// import com.r2s.findInternship.data.mapper.JobPositionMapper;
// import com.r2s.findInternship.service.JobPositionService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @RequestMapping(ApiURL.JOB_POSITION_GENERAL)
// public class JobPositionGeneralController {
// @Autowired
// private JobPositionService jobPositionService;
// @Autowired
// private JobPositionMapper jobPositionMapper;

// @GetMapping("")
// public ResponseEntity<?> findAll() {
// return ResponseEntity.ok(this.jobPositionService.findAll());
// }

// @PostMapping("")
// public ResponseEntity<?> create(@RequestBody Position jobPosition) {
// return new
// ResponseEntity<PositionDTO>(this.jobPositionService.create(jobPositionMapper.toDTO(jobPosition)),
// HttpStatus.CREATED);
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.jobPositionService.findById(id));
// }
// }

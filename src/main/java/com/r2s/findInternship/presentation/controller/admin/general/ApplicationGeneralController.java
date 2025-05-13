package com.r2s.findInternship.presentation.controller.admin.general;

// import java.time.LocalDateTime;
// import java.util.Map;

// import com.r2s.findInternship.common.MessageResponse;
// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.MessageSource;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
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
// import com.r2s.findInternship.common.MailResponse;
// import com.r2s.findInternship.common.enumeration.EMailType;
// import com.r2s.findInternship.data.dto.CandidateApplicationDTO;
// import com.r2s.findInternship.data.dto.CandidateDTO;
// import com.r2s.findInternship.data.dto.JobDTO;
// import com.r2s.findInternship.service.CandidateApplicationService;
// import com.r2s.findInternship.service.CandidateService;
// import com.r2s.findInternship.service.JobService;
// import com.r2s.findInternship.service.MailService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.APPLICATION_GENERAL)
// public class ApplicationGeneralController {
// @Autowired
// private CandidateApplicationService candidateApplicationService;
// @Autowired
// private MailService mailService;
// @Autowired
// private JobService jobService;
// @Autowired
// private CandidateService candidateService;
// @Autowired
// private MessageSource messageSource;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no, @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.candidateApplicationService.findAll(no,
// limit));
// }

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> create(@RequestPart("apply") String apply,
// @RequestPart(name = "fileCV", required = false) MultipartFile fileCV) {

// CandidateApplicationDTO candidateApplicationDTO =
// candidateApplicationService.readJson(apply, fileCV);
// // check job apply
// JobDTO job =
// jobService.findById(candidateApplicationDTO.getJobDTO().getId());
// candidateApplicationDTO.setJobDTO(job);
// // check candidate apply
// CandidateDTO candidateDTO =
// candidateService.findById(candidateApplicationDTO.getCandidateDTO().getId());
// candidateApplicationDTO.setCandidateDTO(candidateDTO);
// candidateApplicationDTO.setCV(candidateApplicationDTO.getCandidateDTO().getCV());
// // check apply
// if
// (candidateApplicationService.existsByJobIdAndCandidateId(candidateApplicationDTO.getJobDTO().getId(),
// candidateApplicationDTO.getCandidateDTO().getId()) == false) {
// return ResponseEntity.ok(new MessageResponse(500, String.format(
// this.messageSource.getMessage("error.ApplyAlready", null, null),
// candidateApplicationDTO.getId()),
// null));
// }
// // create Date
// // applyListDTO.setCreatedDate(LocalDateTime.now());
// // MailResponse mailResponse = new MailResponse();

// // mailResponse.setTo(job.getHrDTO().getUserCreationDTO().getEmail());
// // mailResponse.setTypeMail(EMailType.ApplyJob);
// // mailResponse.setCv(applyListDTO.getCV());
// // mailResponse.setApply();
// // tam thoi khong gui mail
// // mailService.send(mailResponse);
// return new
// ResponseEntity<>(this.candidateApplicationService.create(candidateApplicationDTO),
// HttpStatus.CREATED);
// }

// @GetMapping("/check-exists")
// public ResponseEntity<?> checkByJobIdAndCandidateId(@RequestParam int jobId,
// @RequestParam int candidateId) {
// return
// ResponseEntity.ok(this.candidateApplicationService.existsByJobIdAndCandidateId(jobId,candidateId));
// }

// @GetMapping("/{id}")
// public ResponseEntity<?> findById(@PathVariable int id) {
// return ResponseEntity.ok(this.candidateApplicationService.findById(id));
// }

// @GetMapping("/candidate/{id}")
// public ResponseEntity<?> findAllByCandidateId(@PathVariable int id,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return
// ResponseEntity.ok(this.candidateApplicationService.findAllByCandidateId(id,
// no, limit));
// }

// @GetMapping("/job/{id}")
// public ResponseEntity<?> findAllByJobId(@PathVariable int id,
// @RequestParam(defaultValue = PageDefault.NO) int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.candidateApplicationService.findAllByJobId(id,
// no, limit));
// }

// @PutMapping("/{id}")
// public ResponseEntity<?> update(@PathVariable int id, @RequestBody
// CandidateApplicationDTO dto) {
// return ResponseEntity.ok(this.candidateApplicationService.update(id, dto));
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deleteById(@PathVariable int id) {
// this.candidateApplicationService.deleteById(id);
// return ResponseEntity.ok("DELETED");
// }
// }

package com.r2s.findInternship.presentation.controller.admin;

// import java.util.HashMap;
// import java.util.Map;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.MessageSource;
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

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;
// import com.r2s.findInternship.data.dto.CandidateCreationDTO;
// import com.r2s.findInternship.data.dto.CandidateDTO;
// import com.r2s.findInternship.data.dto.PartnerCreationDTO;
// import com.r2s.findInternship.data.dto.UniversityCreationDTO;
// import com.r2s.findInternship.data.dto.UniversityDTO;
// import com.r2s.findInternship.data.mapper.HRMapper;
// import com.r2s.findInternship.data.mapper.UserMapper;

// import com.r2s.findInternship.service.CandidateService;
// import com.r2s.findInternship.service.HRService;
// import com.r2s.findInternship.service.PartnerService;
// import com.r2s.findInternship.service.UniversityService;
// import com.r2s.findInternship.service.UserService;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping(ApiURL.ADMIN_USER)
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasAuthority('Role_Admin')")
// public class AdminUserController {
// @Autowired
// private UserMapper userMapper;
// @Autowired
// private UserService userService;
// @Autowired
// private UniversityService universityService;
// @Autowired
// private CandidateService candidateService;
// @Autowired
// private HRService hrService;
// @Autowired
// private HRMapper hrMapper;
// @Autowired
// private MessageSource messageSource;
// @Autowired
// private PartnerService partnerService;

// @GetMapping("")
// public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO)
// int no,
// @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// return ResponseEntity.ok(this.userService.findAll(no, limit));
// }

// @GetMapping("/get-username/{username}")
// public ResponseEntity<?> findByUsername(@PathVariable String username) {
// return
// ResponseEntity.ok(this.userMapper.toDetailsDTO(userService.findByUsername(username)));
// }

// @GetMapping("/get-id/{id}")
// public ResponseEntity<?> findById(@PathVariable long id) {
// return ResponseEntity.ok(userService.findById(id));
// }

// @DeleteMapping("/{username}")
// public ResponseEntity<?> deleteByUsername(@PathVariable String username) {
// return ResponseEntity.ok(userService.deleteByUsername(username));
// }

// @GetMapping("/re/{username}")
// public ResponseEntity<?> recoverByUsername(@PathVariable String username) {
// this.userService.recoverByUsername(username);
// return ResponseEntity.ok("User recovered!");
// }

// @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> create(@RequestPart(value = "candidate", required =
// false) String candidateJson,
// @RequestPart(value = "fileCV", required = false) MultipartFile fileCV,
// @RequestPart(value = "fileAvatar", required = false) MultipartFile
// fileAvatar,
// @RequestPart(value = "partner", required = false) String partnerJson,
// @RequestPart(value = "university", required = false) String universityJson,
// @RequestPart(value = "fileLogo", required = false) MultipartFile fileLogo,
// @RequestPart(value = "hr", required = false) String hrJson) {
// // if (candidateJson != null) {
// // CandidateCreationDTO candidateCreateDTO =
// candidateService.readJson(candidateJson, fileCV, fileAvatar);
// // return new
// ResponseEntity<CandidateDTO>(candidateService.create(candidateCreateDTO),
// HttpStatus.CREATED);
// // }

// // if (partnerJson != null) {
// // UniversityCreationDTO universityCreateDTO =
// universityService.readJson(universityJson, partnerJson,
// // fileAvatar, fileLogo);

// // return new
// ResponseEntity<UniversityDTO>(this.universityService.createFirst(universityCreateDTO),
// // HttpStatus.CREATED);
// // }

// // if (hrJson != null) {
// // HRDTO hrDTO = hrService.readJson(hrJson, fileAvatar);
// // return new
// ResponseEntity<HRDTO>(hrMapper.toDTO(hrMapper.toEntity(hrService.create(hrDTO))),
// // HttpStatus.CREATED);
// // }
// // Map<String, String> response = new HashMap<>();
// // response.put("status", "400");
// // response.put("message", messageSource.getMessage("error.missingvalue",
// null, null));
// // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
// return null;
// }

// @PutMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
// MediaType.MULTIPART_FORM_DATA_VALUE })
// public ResponseEntity<?> update(@RequestPart(value = "candidate", required =
// false) String candidateJson,
// @RequestPart(value = "fileCV", required = false) MultipartFile fileCV,
// @RequestPart(value = "fileAvatar", required = false) MultipartFile
// fileAvatar,
// @RequestPart(value = "partner", required = false) String partnerJson,
// @RequestPart(value = "university", required = false) String universityJson,
// @RequestPart(value = "fileLogo", required = false) MultipartFile fileLogo,
// @RequestPart(value = "hr", required = false) String hrJson) {
// // if (candidateJson != null) {
// // CandidateCreationDTO candidateCreateDTO =
// candidateService.readJson(candidateJson, fileCV, fileAvatar);
// // return ResponseEntity.ok(candidateService.update(candidateCreateDTO));
// // }
// // if (partnerJson != null) {
// // PartnerCreationDTO partnerCreateDTO =
// partnerService.readJson(partnerJson);
// // return ResponseEntity.ok(partnerService.updateUser(partnerCreateDTO));
// // }
// // if (hrJson != null) {
// // HRDTO hrDTO = hrService.readJson(hrJson, fileAvatar);
// // return ResponseEntity.ok(this.hrService.updateUser(hrDTO));
// // }
// // Map<String, String> response = new HashMap<>();
// // response.put("status", "400");
// // response.put("message", messageSource.getMessage("error.missingvalue",
// null, null));
// // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
// return null;
// }
// }
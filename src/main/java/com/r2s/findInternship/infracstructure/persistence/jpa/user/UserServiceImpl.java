package com.r2s.findInternship.infracstructure.persistence.jpa.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.*;
import com.r2s.findInternship.application.dto.user.UserCreationDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.application.dto.user.UserProfileDTO;
import com.r2s.findInternship.domain.common.JwtUtils;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.EAuthenticationProvider;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.common.util.Validation;
import com.r2s.findInternship.domain.common.util.oauth.OAuth2UserInfo;
import com.r2s.findInternship.domain.entity.Status;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.RoleRepository;
import com.r2s.findInternship.domain.repository.StatusRepository;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.*;

import com.r2s.findInternship.domain.service.CandidateService;
import com.r2s.findInternship.domain.service.RoleService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.infracstructure.exception.exception_v2.ConflictExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v2.InternalServerErrorExceptionV2;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InvalidOldPasswordException;
import com.r2s.findInternship.infracstructure.persistence.mapper.RoleMapper;
import com.r2s.findInternship.infracstructure.persistence.mapper.UserMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Validation validation;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuditorAware<Long> auditorAware;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private HttpServletRequest request;

    public final Logger logger = LoggerFactory.getLogger("info");

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.build(findByEmail(email));
    }

    @Override
    public void updateTokenForgetPassword(String email, String token) {
        User user = this.findByEmail(email);
        user.setPasswordForgotToken(token);
        userRepository.save(user);
    }

    @Override
    public void updateTokenActive(String email, String token) {
        User user = this.findByEmail(email);
        user.setTokenActive(token);
        userRepository.save(user);
    }

    //    OTP ADD 2024/11/10 PhucHT START
    @Override
    public void updateOtpActive(String email, String otp, LocalDateTime otpExpiryDate) {
        User user = this.findByEmail(email);
        user.setOtpActive(otp);
        user.setOtpExpiryDate(otpExpiryDate);
        userRepository.save(user);
    }

    @Override
    public void updateOtpForgetPassword(String email, String otp, LocalDateTime otpExpiryDate) {
        User user = this.findByEmail(email);
        user.setPasswordForgotOtp(otp);
        user.setOtpExpiryDate(otpExpiryDate);
        userRepository.save(user);
    }
    //    OTP ADD 2024/11/10 PhucHT END

    @Override
    public ResponseEntity<?> authenticateUser(User userOAuth, Authentication authentication) {
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userOAuth.getEmail(), userOAuth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        long id = userOAuth.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new ResponseEntity<JwtResponseDTO>(
                new JwtResponseDTO(jwt, user.getEmail(), roles.get(0), userOAuth.getAvatar(), id,
                        null),
                HttpStatus.CREATED);

    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    public MessageResponse existsByEmail(String email) {
        String message = messageSource.getMessage(
                userRepository.existsByEmail(email) ? "error.emailExists" : "error.emailNotExists",
                null, LocaleContextHolder.getLocale()
        );
        return new MessageResponse(HttpServletResponse.SC_OK, message, request.getServletPath());
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        Page<UserDTO> page = this.userRepository.findAll(PageRequest.of(no, limit)).map(u -> userMapper.toDTO(u));

        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public String encodePass(String pass) {
        return this.passwordEncoder.encode(pass);
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(messageSource.getMessage("error.userAuthen", null, null)));

        if (checkValidOldPassword(user.getPassword(), changePasswordDTO.getOldPassword())) {
            if (!validation.passwordValid(changePasswordDTO.getNewPassword())) {
                throw new ValidationExceptionV2(Collections.singletonMap("error", messageSource.getMessage("error.passwordRegex", null, null)));
            }

            if (passwordEncoder.matches(changePasswordDTO.getNewPassword(), user.getPassword())) {
                throw new ValidationExceptionV2(Collections.singletonMap("error",
                        messageSource.getMessage("error.passwordSameAsOld", null, null)));
            }

            if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
                throw new ValidationExceptionV2(Collections.singletonMap("error", messageSource.getMessage("error.passwordMismatch", null, null)));
            }

            user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userRepository.save(user);

        } else {
            throw new ValidationExceptionV2(Collections.singletonMap("error",
                    messageSource.getMessage("error.passwordIncorrect", null, null)));
        }

        return true;
    }

    //    OTP ADD 2024/11/10 PhucHT START
//    @Override
//    public void changePasswordByOtp(ChangePasswordByOtpDTO changePasswordByOtpDTO) {
//        User user = this.userRepository.findByPasswordForgotOtp(changePasswordByOtpDTO.getOtp())
//                .orElseThrow(() -> new ValidationExceptionV2(Collections.singletonMap("OTP ERROR: ",
//                        this.messageSource.getMessage("error.otpInvalid", null, LocaleContextHolder.getLocale()))));
//
//        if (user.getOtpExpiryDate() == null || LocalDateTime.now().isAfter(user.getOtpExpiryDate())) {
//            throw new ValidationExceptionV2(Collections.singletonMap("OTP ERROR: ",
//                    this.messageSource.getMessage("error.otpExpired", null, LocaleContextHolder.getLocale())));
//        }
//
//        if (!validation.passwordValid(changePasswordByOtpDTO.getNewPassword()))
//            throw new InternalServerErrorException(messageSource.getMessage("error.passwordRegex", null, LocaleContextHolder.getLocale()));
//
//        if (!changePasswordByOtpDTO.getNewPassword().equals(changePasswordByOtpDTO.getConfirmPassword()))
//            throw new InternalServerErrorException(messageSource.getMessage("error.passwordMismatch", null, LocaleContextHolder.getLocale()));
//
//        user.setPassword(passwordEncoder.encode(changePasswordByOtpDTO.getNewPassword()));
//        user.setPasswordForgotOtp("");
//        user.setOtpExpiryDate(null);
//        this.userRepository.save(user);
//    }

    @Override
    public MessageResponse verifyOtp(String otp) {
        User user = userRepository.findByPasswordForgotOtp(otp)
                .orElseThrow(() -> new ValidationExceptionV2(Map.of(
                        "OTP ERROR", messageSource.getMessage("error.otpInvalid", null, LocaleContextHolder.getLocale())
                )));

        if (user.getOtpExpiryDate() == null || LocalDateTime.now().isAfter(user.getOtpExpiryDate())) {
            throw new ValidationExceptionV2(Map.of(
                    "OTP ERROR", messageSource.getMessage("error.otpExpired", null, LocaleContextHolder.getLocale())
            ));
        }

        // Tạo token reset mật khẩu (giữ userId để xác định người dùng ở bước tiếp theo)
        String resetToken = UUID.randomUUID().toString();
        user.setPasswordForgotOtp(resetToken);
        userRepository.save(user);

        return new MessageResponse(HttpServletResponse.SC_OK, resetToken, request.getServletPath());
    }

    @Override
    public MessageResponse resetPassword(ChangePasswordByOtpDTO dto) {
        User user = userRepository.findByPasswordForgotOtp(dto.getResetToken())
                .orElseThrow(() -> new ValidationExceptionV2(Map.of(
                        "RESET ERROR", messageSource.getMessage("error.token", null, LocaleContextHolder.getLocale())
                )));

        if (!validation.passwordValid(dto.getNewPassword())) {
            throw new InternalServerErrorException(messageSource.getMessage("error.passwordRegex", null, LocaleContextHolder.getLocale()));
        }

        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new InternalServerErrorException(messageSource.getMessage("error.passwordMismatch", null, LocaleContextHolder.getLocale()));
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        user.setPasswordForgotOtp(null);
        user.setOtpExpiryDate(null);
        userRepository.save(user);

        return new MessageResponse(HttpServletResponse.SC_OK, "Đổi mật khẩu thành công", request.getServletPath());
    }
    //    OTP ADD 2024/11/10 PhucHT END

    @Override
    public String checkStatusUser(Status status) {
        String message = "";
        switch (status.getId()) {
            case 1:
                message = "";
                break;
            case 2:
                message = messageSource.getMessage("error.notAvailable", null, null);
                break;
            case 3:
                message = messageSource.getMessage("error.lockUser", null, null);
                break;
            case 4:
                message = messageSource.getMessage("error.disableUser", null, null);
                break;
        }
        return message;
    }

    @Override
    public UserProfileDTO findById(long id) {
        return null;
    }

    @Override
    public MessageResponse updateMailReceive(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("userId", id)));
        user.setMailReceive(!user.isMailReceive());
        userRepository.save(user);

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public boolean checkTimeTokenChangePassword(String time) {
        int partOne = 1;
        int partTwo = 2;
        int countDown = 10;
        String[] parts = time.split("%");
        String timeToken = parts[partOne] + " " + parts[partTwo];
        LocalDateTime localDateTime = LocalDateTime.parse(timeToken,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return LocalDateTime.now().isBefore(localDateTime.plusMinutes(countDown));
    }

    @Override
    public User findByEmail(String email) {
        String errorMessage = this.messageSource.getMessage("error.emailFormat", null, Locale.getDefault());
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap(errorMessage, email)));
    }

    @Override
    public boolean checkValidOldPassword(String oldPass, String confirmPass) {
        return passwordEncoder.matches(confirmPass, oldPass);
    }

    @Override
    public List<Object[]> getGenderStatistics() {
        return null;
    }

    @Override
    public Long countByCreatedDate(Date from, Date to) {
        return null;
    }

    @Override
    public List<Object[]> getStatusStatistics() {
        return null;
    }

    @Override
    public List<Object[]> getNewStatistics() { // created date within 1 month
        return null;
    }

    @Override
    public List<Object[]> getRoleStatistics() {
        return null;
    }

    @Override
    public PaginationDTO findAllByEmailLike(String email, int no, int limit) {
        Pageable page = PageRequest.of(no, limit);
        Page<User> userPage = userRepository.findALLByEmailLike(email, page);
        List<UserDTO> users = userPage.getContent().stream().map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());

        return new PaginationDTO(users, userPage.isFirst(), userPage.isLast(), userPage.getTotalPages(),
                userPage.getTotalElements(), userPage.getSize(), userPage.getNumberOfElements());
    }

    @Override
    public User createAfterLoginOAuth(OAuth2UserInfo oAuth2UserInfo) {
        UUID uuid = UUID.randomUUID();
        User user = new User();
        String provider = oAuth2UserInfo.getClientName();

        // create with google
        if (provider.toUpperCase().equals(EAuthenticationProvider.GOOGLE.toString())) {
            user.setEmail(oAuth2UserInfo.getEmail());
            user.setLastName(oAuth2UserInfo.getLastName());
            user.setFirstName(oAuth2UserInfo.getFistName());
            user.setAvatar(oAuth2UserInfo.getAvatar());
            user.setPassword(
                    new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10)
                            .encode(String.valueOf(uuid)));
            user.setCreatedDate(new Date());
            user.setAuthProvider(String.valueOf(provider));
            user.setRole(roleMapper.toEntity(roleService.findByName("Role_Candidate")));
            user.setStatus(this.statusService.findByName(Estatus.Active));
            user.setMailReceive(true);
            user.setLocation(oAuth2UserInfo.getLocale());

            return user;
            // create with facebook
        } else {
            user.setEmail(oAuth2UserInfo.getEmail());
            user.setLastName("");
            user.setFirstName(oAuth2UserInfo.getName());
            user.setAvatar(oAuth2UserInfo.getAvatar());
            user.setPassword(
                    new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10)
                            .encode(String.valueOf(uuid)));
            user.setCreatedDate(new Date());
            user.setAuthProvider(provider);
            user.setRole(roleMapper.toEntity(roleService.findByName("Role_Candidate")));
            user.setStatus(this.statusService.findByName(Estatus.Active));

            return user;
        }
    }

    @Override
    public void updateAfterLoginOAuth(User user, OAuth2UserInfo oAuth2UserInfo, EAuthenticationProvider provider) {
        if (oAuth2UserInfo.getClientName().equals("Google")) {
            user.setStatus(this.statusService.findByName(Estatus.Active));
            user.setLastName(oAuth2UserInfo.getLastName());
            user.setFirstName(oAuth2UserInfo.getFistName());
            user.setAvatar(oAuth2UserInfo.getAvatar());
            user.setAuthProvider(String.valueOf(provider));
            user.setCreatedDate(new Date());
            userRepository.save(user);
        }
    }

    @Override
    public Long getCurrentUserId() {
        return auditorAware.getCurrentAuditor().orElse(null);
    }

    @Override
    public UserDTO create(UserCreationDTO userCreationDTO, MultipartFile fileAvatar, ERole eRole) {
        // check existing user info
        Map<String, Object> errors = new HashMap<String, Object>();
        if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
            errors.put("email", messageSource.getMessage("error.emailExists", null, Locale.getDefault()));
        }

        if (errors.size() > 0) {
            throw new ConflictExceptionV2(errors);
        }

        // set info for user
        User user = userMapper.toEntity(userCreationDTO);
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        user.setAvatar(fileService.uploadFile(fileAvatar));
        // set default role and status
        user.setRole(
                roleRepository.findByName(eRole.toString())
                        .orElseThrow(
                                () -> new InternalServerErrorExceptionV2(
                                        Collections.singletonMap(eRole.toString(), "NOT EXISTS"))));
        user.setStatus(
                statusRepository.findByName(Estatus.Not_Active.toString())
                        .orElseThrow(
                                () -> new InternalServerErrorExceptionV2(
                                        Collections.singletonMap(Estatus.Not_Active.toString(), "NOT EXISTS IN"))));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionV2(Collections.singletonMap("UserID", id)));

        // check existing user info in another one
        Map<String, Object> errors = new HashMap<String, Object>();
        if (userRepository.existsByIdNotAndEmail(id, userProfileDTO.getEmail())) {
            errors.put("email", userProfileDTO.getEmail());
        }
        if (errors.size() > 0) {
            throw new ConflictExceptionV2(errors);
        }

        User updateUser = userMapper.toEntity(userProfileDTO);
        updateUser.setId(oldUser.getId());
        updateUser.setEmail(oldUser.getEmail());
        updateUser.setPassword(oldUser.getPassword());

        // check update file Avatar
        if (!StringUtils.equals(updateUser.getAvatar(), oldUser.getAvatar()) || (fileAvatar != null)) {
            fileService.deleteFile(oldUser.getAvatar());
            updateUser.setAvatar(fileService.uploadFile(fileAvatar));
        }

        updateUser.setRole(oldUser.getRole());
        updateUser.setStatus(oldUser.getStatus());

        return userMapper.toDTO(userRepository.save(updateUser));
    }

    @Override
    public UserDTO getCurrentLoginUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent() ? userMapper.toDTO(user.get()) : null;
    }
}
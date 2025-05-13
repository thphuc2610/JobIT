package com.r2s.findInternship.domain.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.r2s.findInternship.application.dto.ChangePasswordByOtpDTO;
import com.r2s.findInternship.application.dto.ChangePasswordDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.user.UserCreationDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.application.dto.user.UserProfileDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.EAuthenticationProvider;
import com.r2s.findInternship.domain.common.enumeration.ERole;
import com.r2s.findInternship.domain.common.util.oauth.OAuth2UserInfo;
import com.r2s.findInternship.domain.entity.Status;
import com.r2s.findInternship.domain.entity.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    boolean existsById(long id);

    UserProfileDTO findById(long id);

    MessageResponse updateMailReceive(long id);

    ResponseEntity<?> authenticateUser(User userOAuth, Authentication authentication);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    String checkStatusUser(Status status);

    boolean checkTimeTokenChangePassword(String time);

    PaginationDTO findAll(int no, int limit);

    boolean changePassword(ChangePasswordDTO changePasswordDTO);

    boolean checkValidOldPassword(String oldPass, String newPass);

    Long countByCreatedDate(Date from, Date to);

    List<Object[]> getGenderStatistics();

    List<Object[]> getRoleStatistics();

    List<Object[]> getStatusStatistics();

    List<Object[]> getNewStatistics();

    MessageResponse existsByEmail(String email);

    String encodePass(String pass);

    User findByEmail(String email);

    PaginationDTO findAllByEmailLike(String username, int no, int limit);

    User createAfterLoginOAuth(OAuth2UserInfo oAuth2UserInf);

    void updateAfterLoginOAuth(User user, OAuth2UserInfo oAuth2UserInfo, EAuthenticationProvider provider);

    void updateTokenForgetPassword(String email, String token);

    void updateTokenActive(String email, String token);

    //    OTP ADD 2024/11/10 PhucHT START
    //    void changePasswordByToken(ChangePasswordByTokenDTO changePasswordByTokenDTO);

    void updateOtpActive(String email, String otp, LocalDateTime otpExpiryDate);

    void updateOtpForgetPassword(String email, String otp, LocalDateTime otpExpiryDate);

//    void changePasswordByOtp(ChangePasswordByOtpDTO changePasswordByOtpDTO);

    MessageResponse verifyOtp(String otp);

    MessageResponse resetPassword(ChangePasswordByOtpDTO dto);
    //    OTP ADD 2024/11/10 PhucHT END

    Long getCurrentUserId();

    UserDTO create(UserCreationDTO userCreationDTO, MultipartFile fileAvatar, ERole eRole);

    UserDTO update(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar);

    UserDTO getCurrentLoginUser();
}
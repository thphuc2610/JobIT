package com.r2s.findInternship.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.r2s.findInternship.application.dto.ErrorMessageResponseDTO;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;

import com.r2s.findInternship.application.dto.JwtResponseDTO;
import com.r2s.findInternship.application.dto.LoginRequestDTO;
import com.r2s.findInternship.domain.common.JwtUtils;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.persistence.jpa.user.UserDetailsImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
// @RequestMapping(ApiURL.LOGIN)
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final MessageSource messageSource;
    private final HttpServletRequest request;

    public final Logger LOGGER = LoggerFactory.getLogger("info");
    @Value("${url.redirect.path}")
    private String urlOAuth2;

    @PostMapping("api/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        User userDto = userService.findByEmail(user.getEmail());

        // Kiểm tra trạng thái người dùng
        if (userDto.getStatus().getId() != Estatus.activeStatus) {
            String errorMessage = messageSource.getMessage("error.notAvailable", null, LocaleContextHolder.getLocale());
            ErrorMessageResponseDTO errorResponse = new ErrorMessageResponseDTO(
                    LocalDateTime.now(),
                    HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    Thread.currentThread().getStackTrace()[1].toString(),
                    errorMessage,
                    request.getRequestURI());
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        String message = userService.checkStatusUser(userDto.getStatus());

        long id = userDto.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LOGGER.info("{} has successfully logged in.", user.getEmail());

        return new ResponseEntity<>(new JwtResponseDTO(jwt, user.getEmail(), roles.get(0),
                userDto.getAvatar(), id, message.isEmpty() ? null : message), HttpStatus.CREATED);
    }


    @GetMapping("/")
    public RedirectView loginOauthGoogleSuccess(@RequestParam(value = "token") String token,
                                                @RequestParam("type") String type, @RequestParam("email") String email,
                                                @RequestParam("role") String role, @RequestParam("avatar") String avatar,
                                                @RequestParam("id") int id) {
        String redirectUrl = "http://localhost:3000/auth/social/google?token=" + token + "&type=" + type
                + "&email=" + email + "&role=" + role + "&avatar=" + avatar + "&id=" + id;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;

    }

    @GetMapping("/failure")
    public ResponseEntity<?> loginFailure() {
        return new ResponseEntity<>("Có lỗi xảy ra", HttpStatus.BAD_REQUEST);
    }
}
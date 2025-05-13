package com.r2s.findInternship.infracstructure.exception.exception_v2;

import com.r2s.findInternship.domain.constant.HttpStatusConstant;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.r2s.findInternship.application.dto.ErrorMessageResponseDTO;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandlerV2 {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private View error;

    @ExceptionHandler(ResourceNotFoundExceptionV2.class)
    public ResponseEntity<?> handleNotFoundException(ResourceNotFoundExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictExceptionV2.class)
    public ResponseEntity<?> handleConflictException(ConflictExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationExceptionV2.class)
    public ResponseEntity<?> handleValidationExceptionV2(ValidationExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedExceptionV2.class)
    public ResponseEntity<?> handleAccessDeniedExceptionV2(AccessDeniedExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JsonProcessExceptionV2.class)
    public ResponseEntity<?> handleJsonProcessExceptionV2(JsonProcessExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorExceptionV2.class)
    public ResponseEntity<?> handleJsonProcessExceptionV2(InternalServerErrorExceptionV2 ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<?> handleAuthenticationExceptions(Exception ex, HttpServletRequest request) {
        // Lấy thông báo lỗi từ messageSource
        String errorMessage = messageSource.getMessage("error.loginInvalid", null, LocaleContextHolder.getLocale());

        // Tạo ExceptionCustomV2 với trạng thái HTTP là 401 (Unauthorized) và lỗi cụ thể
        ExceptionCustomV2 exceptionCustomV2 = new ExceptionCustomV2(
                errorMessage,
                errorMessage,
                toString(),
                HttpStatusConstant.UNAUTHORIZED
        );

        return createErrorResponse(exceptionCustomV2, request, HttpStatus.UNAUTHORIZED);
    }


    private ResponseEntity<?> createErrorResponse(ExceptionCustomV2 ex, HttpServletRequest request, HttpStatus status) {
        ErrorMessageResponseDTO errorResponse = new ErrorMessageResponseDTO(
                LocalDateTime.now(),
                status.value(),
                ex.getErrors(),
                ex.getTrace(),
                ex.getMessage(),
                request.getServletPath()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
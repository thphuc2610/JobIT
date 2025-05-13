package com.r2s.findInternship.presentation.controller;

import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.ActiveService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.ACTIVE)
@Validated
@RequiredArgsConstructor
public class ActiveController {
    private final ActiveService activeService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<?> activeAccountCandidate(@Valid @RequestParam(name = "otp") String otp) {
        try {
            this.activeService.activeAccount(otp);

            String redirectUrl = "http://localhost:3000/auth/confirmActive?status=success&message=" +
                    messageSource.getMessage("error.activeUserSuccessfull", null, null);

            MessageResponse response = new MessageResponse(
                    HttpServletResponse.SC_OK,
                    "User activated successfully.",
                    redirectUrl
            );
            return ResponseEntity.ok(response);

        } catch (InternalServerErrorException ex) {
            String redirectUrl = "http://localhost:3000/auth/confirmActive?status=failed&message=" + ex.getMessage();

            MessageResponse response = new MessageResponse(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    redirectUrl
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception ex) {
            String redirectUrl = "http://localhost:3000/auth/confirmActive?status=failed&message=Unexpected error occurred";

            MessageResponse response = new MessageResponse(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unexpected error occurred.",
                    redirectUrl
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
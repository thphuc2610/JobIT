package com.r2s.findInternship.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorMessageResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private Object errors;
    private String trace;
    private String message;
    private String path;
}
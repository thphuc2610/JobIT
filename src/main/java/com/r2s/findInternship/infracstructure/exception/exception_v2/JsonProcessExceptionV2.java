package com.r2s.findInternship.infracstructure.exception.exception_v2;

import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.r2s.findInternship.domain.constant.HttpStatusConstant;

public class JsonProcessExceptionV2 extends ExceptionCustomV2 {

    public JsonProcessExceptionV2(JsonProcessingException ex) {
        super("JSON INVALID PAYLOAD", extractErrorMessage(ex), getStackTraceAsString(ex), HttpStatusConstant.BAD_REQUEST);
    }

    private static Object extractErrorMessage(JsonProcessingException ex) {
        String fieldName = getFieldError(ex);
        return fieldName == null ? ex.getOriginalMessage() : Collections.singletonMap(fieldName, "UNRECOGNIZED");
    }

    private static String getFieldError(JsonProcessingException e) {
        String fieldName = null;
        if (e instanceof JsonMappingException) {
            JsonMappingException jsonMappingException = (JsonMappingException) e;
            for (JsonMappingException.Reference reference : jsonMappingException.getPath()) {
                if (reference.getFieldName() != null) {
                    fieldName = reference.getFieldName();
                }
            }
        }
        return fieldName;
    }

    private static String getStackTraceAsString(Exception ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
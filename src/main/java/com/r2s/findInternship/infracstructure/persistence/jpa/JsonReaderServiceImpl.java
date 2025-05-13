package com.r2s.findInternship.infracstructure.persistence.jpa;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2s.findInternship.domain.service.JsonReaderService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.JsonProcessExceptionV2;

@Service
public class JsonReaderServiceImpl implements JsonReaderService<Object> {

    @Override
    public <U> U readValue(String content, Class<U> valueType) {
        try {
            return new ObjectMapper().readValue(content, valueType);
        } catch (JsonProcessingException e) {
            throw new JsonProcessExceptionV2(e);
        }
    }
}

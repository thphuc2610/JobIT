package com.r2s.findInternship.domain.service;

public interface JsonReaderService<T> {
    <U> U readValue(String content, Class<U> valueType);
}
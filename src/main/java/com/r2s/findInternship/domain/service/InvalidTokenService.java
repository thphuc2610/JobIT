package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.domain.common.MessageResponse;

public interface InvalidTokenService {
    void storeToken(String token);

    boolean isTokenInvalid(String token);

    MessageResponse logout(String token);
}
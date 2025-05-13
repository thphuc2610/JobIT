package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.domain.common.MessageResponse;

public interface ActiveService {
    MessageResponse activeAccount(String otp);
}
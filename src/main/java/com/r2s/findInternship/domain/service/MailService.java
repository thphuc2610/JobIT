package com.r2s.findInternship.domain.service;

import com.r2s.findInternship.domain.common.MailResponse;
import com.r2s.findInternship.domain.common.MessageResponse;

public interface MailService {
	MessageResponse send(MailResponse mailResponse);

	MessageResponse sendMailActiveAndroid(String email);

	MessageResponse sendMailActive(String email);

	MessageResponse sendMailForgotPassword(String email, boolean flag);
}
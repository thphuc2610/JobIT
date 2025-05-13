package com.r2s.findInternship.infracstructure.persistence.jpa.user;

import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.ActiveService;
import com.r2s.findInternship.domain.service.StatusService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActiveServiceImpl implements ActiveService {
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final StatusService statusService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public MessageResponse activeAccount(String otp) {
        User user = this.userRepository.findByOtpActive(otp);
        if (user == null)
            throw new InternalServerErrorException(messageSource
                    .getMessage("error.otpInvalid", null, null));

        if (user.getOtpExpiryDate() == null || LocalDateTime.now().isAfter(user.getOtpExpiryDate())) {
            throw new InternalServerErrorException(messageSource
                    .getMessage("error.otpIsExpired", null, null));
        }

        user.setStatus(this.statusService.findByName(Estatus.Active));
        user.setOtpActive("");
        user.setOtpExpiryDate(null);

        this.userRepository.save(user);
        return new MessageResponse(HttpServletResponse.SC_OK, "", httpServletRequest.getServletPath());
    }
}
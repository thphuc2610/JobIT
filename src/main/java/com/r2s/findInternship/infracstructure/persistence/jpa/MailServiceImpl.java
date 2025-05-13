package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.r2s.findInternship.domain.common.OtpUtils;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.r2s.findInternship.domain.common.MailResponse;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.common.enumeration.EMailType;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.User;
import com.r2s.findInternship.domain.repository.UserRepository;
import com.r2s.findInternship.domain.service.MailService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final UserService userService;
    private final ServletContext context;
    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private List<MimeMessage> queue = new ArrayList<MimeMessage>();
    @Value("${url.redirect.path}")
    private String urlRedirect;
    private final HttpServletRequest httpServletRequest;

    @Override
    public MessageResponse send(MailResponse response) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd%HH:mm:ss"));

        UUID uuid = UUID.randomUUID();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(response.getTo());
            Random random = new Random();
            int randomNumber = random.nextInt(9000) + 1000;

            switch (response.getTypeMail()) {
                case ConfirmMail:
                    response.setSubject("Xác thực email cho tài khoản Jobsit.vn");

                    OtpUtils.OtpResponse otpResponseActive = OtpUtils.generateOtpCodeWithExpiry();
                    String otpActive = otpResponseActive.getOtpActive();
                    LocalDateTime otpExpiryDate = otpResponseActive.getExpiryDate();

                    userService.updateOtpActive(response.getTo(), otpActive, otpExpiryDate);
                    response.createMailConfirm(urlRedirect, otpActive);
                    break;

                case ApplyJob:// SEND MAIL HR
                    response.setSubject("Thông Báo Ứng Viên Ứng Tuyển");
                    if (response.getCv() != null) {
                        File file = new File(context.getRealPath("/") + response.getCv());
                        helper.addAttachment("File CV", file);
                    }
                    response.createTemplate();
                    break;

                case ConfirmMailAndroid:
                    response.setSubject(randomNumber + " Xác thực email cho tài khoản App Jobsit");

                    userService.updateTokenActive(response.getTo(), String.valueOf(randomNumber));
                    response.createMailActiveOTP(urlRedirect, randomNumber);
                    break;

                case ForgotPassword:
                    response.setSubject("Yêu cầu đổi mật khẩu tài khoản trên Jobsit.vn");

                    OtpUtils.OtpResponse otpResponseForgotPassword = OtpUtils.generateOtpCodeWithExpiry();
                    String otpForgotPassword = otpResponseForgotPassword.getOtpActive();
                    LocalDateTime otpExpiryPasswordForgot = otpResponseForgotPassword.getExpiryDate();

                    userService.updateOtpForgetPassword(response.getTo(), otpForgotPassword, otpExpiryPasswordForgot);
                    response.createMailForgotPassword(urlRedirect, otpForgotPassword);
                    break;

                case ForgotPasswordOTP:
                    response.setSubject("Yêu cầu đổi mật khẩu tài khoản trên App Jobsit");
                    response.setSubject(randomNumber + " Yêu cầu đổi mật khẩu tài khoản trên App Jobsit");

                    userService.updateTokenForgetPassword(response.getTo(), String.valueOf(randomNumber));
                    response.createMailForgotPasswordOTP(urlRedirect, randomNumber);
                    break;

                case HRApply:
                    if (response.getCv() != null) {
                        File file = new File(context.getRealPath("/") + response.getCv());
                        helper.addAttachment("File", file);
                    }
                    response.createTemplateHRApply();
                    break;

                case ActiveUniversity:
                    response.setSubject("KÍCH HOẠT TÀI KHOẢN THÀNH CÔNG");
                    response.createTemplateActive();
                    break;

                case ActiveCompany:
                    response.setSubject("KÍCH HOẠT TÀI KHOẢN THÀNH CÔNG");
                    break;

                default:
                    throw new InternalServerErrorException("Type mail is incorect!");
            }
            helper.setSubject(response.getSubject());
            helper.setText(response.getMailTemplate(), true);
            queue.add(message);

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    return new MessageResponse(HttpServletResponse.SC_OK, "SEND MAIL", httpServletRequest.getServletPath());
    }

    @Override
    public MessageResponse sendMailActiveAndroid(String email) {
        User user = userService.findByEmail(email);
        if (user.getStatus().getName().equals(Estatus.Active.toString()))
            throw new InternalServerErrorException(messageSource.getMessage("error.alreadyActive", null, null));
        MailResponse mail = new MailResponse();
        mail.setTo(email);// Set email to reset password! Get User by Email => Change Password
        mail.setTypeMail(EMailType.ConfirmMailAndroid);
        this.send(mail);
        return new MessageResponse(HttpServletResponse.SC_OK, "SEND MAIL", httpServletRequest.getServletPath());
    }

    @Override
    public MessageResponse sendMailActive(String email) {
        User user = userService.findByEmail(email);
        if (user.getStatus().getName().equals(Estatus.Active.toString()))
            throw new InternalServerErrorException(messageSource.getMessage("error.alreadyActive",
                    null, null));

        OtpUtils.OtpResponse otpResponse = OtpUtils.generateOtpCodeWithExpiry();

        MailResponse mail = new MailResponse();
        mail.setTo(email);// Set email to reset password! Get User by Email => Change Password
        mail.setTypeMail(EMailType.ConfirmMail);
        this.send(mail);
        return new MessageResponse(HttpServletResponse.SC_OK, "SEND MAIL", httpServletRequest.getServletPath());
    }

    @Override
    public MessageResponse sendMailForgotPassword(String email, boolean flag) {
        if (!userRepository.existsByEmail(email))
            throw new ResourceNotFoundExceptionV2(Collections.singletonMap("Email not found: ", email));
        MailResponse mail = new MailResponse();
        mail.setTo(email);// Set email to reset password! Get User by Email => Change Password

        if (!flag)
            mail.setTypeMail(EMailType.ForgotPassword);
        else
            mail.setTypeMail(EMailType.ForgotPasswordOTP);
        this.send(mail);
        return new MessageResponse(HttpServletResponse.SC_OK, "SEND MAIL", httpServletRequest.getServletPath());
    }

    @Scheduled(fixedDelay = 10000) // DELAY 10s
    public void run() {
        boolean flag = false;
        while (!queue.isEmpty()) {
            MimeMessage message = queue.remove(0);
            try {
                javaMailSender.send(message);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag)
            System.out.println("Send mail successfully");
    }
}
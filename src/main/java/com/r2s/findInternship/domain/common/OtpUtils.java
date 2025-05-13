package com.r2s.findInternship.domain.common;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.time.LocalDateTime;

public class OtpUtils {
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_MINUTES = 10;

    public static OtpResponse generateOtpCodeWithExpiry() {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        digits.setNumberOfCharacters(OTP_LENGTH);

        String otpCode = generator.generatePassword(OTP_LENGTH, digits);
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        return new OtpResponse(otpCode, expiryDate);
    }

    public static class OtpResponse {
        private final String otpActive;
        private final LocalDateTime expiryDate;

        public OtpResponse(String otpActive, LocalDateTime expiryDate) {
            this.otpActive = otpActive;
            this.expiryDate = expiryDate;
        }

        public String getOtpActive() {
            return otpActive;
        }

        public LocalDateTime getExpiryDate() {
            return expiryDate;
        }
    }
}
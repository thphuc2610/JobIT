package com.r2s.findInternship.infracstructure.persistence.jpa.user;

import com.r2s.findInternship.domain.common.JwtUtils;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.InvalidToken;
import com.r2s.findInternship.domain.repository.InvalidTokenRepository;
import com.r2s.findInternship.domain.service.InvalidTokenService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ValidationExceptionV2;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InvalidTokenServiceImpl implements InvalidTokenService {
    private final InvalidTokenRepository invalidTokenRepository;
    private final HttpServletRequest request;

    public void storeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JwtUtils.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String tokenId = claims.getId();
        LocalDateTime expiryTime = claims.getExpiration().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

        // Save UUID và expiryTime vào bảng invalid_token
        InvalidToken invalidToken = new InvalidToken(tokenId, expiryTime);
        invalidTokenRepository.save(invalidToken);
    }

    public boolean isTokenInvalid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtUtils.getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String tokenId = claims.getId();
            return invalidTokenRepository.existsById(tokenId);

        } catch (JwtException ex) {
            return true;
        }
    }

    public MessageResponse logout(String token) {
        if (isTokenInvalid(token)) {
            throw new ValidationExceptionV2(Collections.singletonMap("Token Error: ", token));
        } else {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtUtils.getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String tokenId = claims.getId();
            storeToken(token);
            SecurityContextHolder.clearContext();
            return new MessageResponse(HttpServletResponse.SC_OK, "Logout successful.", request.getRequestURI());
        }
    }
}
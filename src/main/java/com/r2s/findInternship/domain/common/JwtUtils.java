package com.r2s.findInternship.domain.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.r2s.findInternship.domain.common.util.oauth.UserPrincipal;
import com.r2s.findInternship.domain.service.RoleService;
import com.r2s.findInternship.infracstructure.config.AppProperties;
import com.r2s.findInternship.infracstructure.persistence.jpa.user.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import com.r2s.findInternship.domain.constant.Constant;

@Component
public class JwtUtils {
    @Value("${r2s.jwtSecret}")
    private String jwtSecret;

    @Value("${r2s.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private RoleService roleService;
    private AppProperties appProperties;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        claims.put("roles", authentication.getAuthorities().stream()
                .map(item -> new SimpleGrantedAuthority(item.getAuthority()))
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        claims.put("roles", userPrincipal.getAuthorities().stream()
                .map(item -> new SimpleGrantedAuthority(item.getAuthority()))
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public  String getTokenIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // lấy role từ token
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getRolesFromToken(String token) {
        return (List<Map<String, String>>) Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        }
        return false;

    }

    public static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Constant.JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
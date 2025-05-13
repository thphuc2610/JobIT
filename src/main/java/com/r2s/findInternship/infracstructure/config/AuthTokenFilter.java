package com.r2s.findInternship.infracstructure.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.api.gax.rpc.UnauthenticatedException;
import com.r2s.findInternship.domain.repository.InvalidTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.r2s.findInternship.domain.common.JwtUtils;

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private InvalidTokenRepository invalidTokenRepository;

	// @Autowired
	// private UserService userService;
	// @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

				String tokenId = jwtUtils.getTokenIdFromToken(jwt);
				// Check token disable
				if (invalidTokenRepository.existsById(tokenId)) {
					SecurityContextHolder.clearContext();
					throw new IllegalArgumentException("Unauthenticated, token has been disabled");
				}

				String username = jwtUtils.getUsernameFromToken(jwt);
				// lấy roles từ token
				List<Map<String, String>> authorities = jwtUtils.getRolesFromToken(jwt);
				// đưa về dạng simplegrantedauthority
				Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
						.map(item -> new SimpleGrantedAuthority(item.get("authority"))).collect(Collectors.toSet());

				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			// nếu token không an toàn (đã qua edit) thì sẽ throw exception
			throw new IllegalStateException("Token cannot be trusted");
		}
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}
		return null;
	}
}
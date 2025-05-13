package com.r2s.findInternship.infracstructure.exception.exception_v1;

// // import java.io.IOException;

// // import javax.servlet.ServletException;
// // import javax.servlet.http.HttpServletRequest;
// // import javax.servlet.http.HttpServletResponse;

// // import org.springframework.security.access.AccessDeniedException;

// public class CustomAccessDeniedException {

// //	@Override
// //	public void handle(HttpServletRequest request, HttpServletResponse response,
// //			AccessDeniedException accessDeniedException) throws IOException, ServletException {
// //		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
// //        response.getWriter().write("Forbidden!");
// //		
// //	}

// }

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied. You do not have permission to access this resource.");
    }
}

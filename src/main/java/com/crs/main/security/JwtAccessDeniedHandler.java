package com.crs.main.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        String body = String.format("""
                {
                "timestamp": "%s",
                "status" : 403,
                "error" : "Forbidden",
                "message: : "%s",
                "path: : "%s"
                }
                """, LocalDateTime.now(), accessDeniedException.getMessage(), request.getRequestURI());
        response.getWriter().write(body);
    }
}

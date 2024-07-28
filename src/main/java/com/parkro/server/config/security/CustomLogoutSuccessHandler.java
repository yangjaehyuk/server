package com.parkro.server.config.security;

import com.parkro.server.domain.member.service.TokenBlacklistService;
import com.parkro.server.util.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    public CustomLogoutSuccessHandler(JwtTokenProvider jwtTokenProvider, TokenBlacklistService tokenBlacklistService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            tokenBlacklistService.addToken(token);
        }
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}

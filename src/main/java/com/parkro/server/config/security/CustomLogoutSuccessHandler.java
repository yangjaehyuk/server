package com.parkro.server.config.security;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.domain.member.service.TokenBlacklistService;
import com.parkro.server.util.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Log4j2
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;
    private final MemberMapper memberMapper;
    public CustomLogoutSuccessHandler(JwtTokenProvider jwtTokenProvider, TokenBlacklistService tokenBlacklistService, MemberMapper memberMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenBlacklistService = tokenBlacklistService;
        this.memberMapper = memberMapper;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            String username = jwtTokenProvider.getSubject(token);
            memberMapper.updateFCM(PostMemberReq.builder()
                            .fcmToken(null)
                            .username(username)
                    .build());
            tokenBlacklistService.addToken(token);
        }
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}
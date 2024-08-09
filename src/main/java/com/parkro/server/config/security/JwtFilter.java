package com.parkro.server.config.security;

import com.parkro.server.domain.member.service.TokenBlacklistService;
import com.parkro.server.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Jwt 토큰 필터
 *
 * @author 양재혁
 * @since 2024.07.29
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  양재혁       최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Log4j2
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token) && !tokenBlacklistService.isTokenBlacklisted(token))  {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Security context에 인증 정보를 저장했습니다, uri: {}", requestURI);
        } else {
            log.debug("유효한 Jwt 토큰이 없습니다, uri: {}", requestURI);
        }

        chain.doFilter(request, response);
    }
}
package com.parkro.server.util;

import com.parkro.server.domain.member.service.TokenBlacklistService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Jwt Token 생성
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

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;
  private final TokenBlacklistService tokenBlacklistService;
  @Value("${jwt.secret}")
  private String secretKey;
  @Value("${jwt.token-validity-in-seconds}")
  private long tokenValidMillisecond;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // JWT 토큰 생성
  public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);
    Date now = new Date();

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }

  // JWT 토큰에서 인증 정보 조회
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  // 유저 이름 추출
  public String getUserId(String token) {
    return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  // JWT 토큰에서 sub 값을 추출하는 메서드
  public String getSubject(String token) {
    String[] parts = token.split("\\.");
    if (parts.length != 3) {
      throw new IllegalArgumentException("Invalid JWT token");
    }
    String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
    return extractSubFromPayload(payload);
  }

  // Payload JSON 문자열에서 sub 값을 추출하는 메서드
  private String extractSubFromPayload(String payload) {
    String subKey = "\"sub\":\"";
    int startIndex = payload.indexOf(subKey) + subKey.length();
    int endIndex = payload.indexOf("\"", startIndex);
    return payload.substring(startIndex, endIndex);
  }


  // Request header에서 token 꺼내옴
  public String resolveToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
      return token.substring(7);
    }
    return null;
  }

  // JWT 토큰 유효성 체크
  public boolean validateToken(String token) {
    try {
      if (tokenBlacklistService.isTokenBlacklisted(token)) {
        log.info("블랙리스트에 포함된 Jwt 토큰입니다");
        return false;
      }

      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (SecurityException | MalformedJwtException | IllegalArgumentException exception) {
      log.info("잘못된 Jwt 토큰입니다");
    } catch (ExpiredJwtException exception) {
      log.info("만료된 Jwt 토큰입니다");
    } catch (UnsupportedJwtException exception) {
      log.info("지원하지 않는 Jwt 토큰입니다");
    }

    return false;
  }
}
package com.parkro.server.domain.member.controller;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.member.service.TokenBlacklistService;
import com.parkro.server.exception.LoginFailedException;
import com.parkro.server.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 회원
 *
 * @author 양재혁
 * @since 2024.07.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  양재혁      최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @GetMapping()
    public ResponseEntity<PostMemberReq> usernameDetails(@RequestParam("user") String username) {

        Optional<PostMemberReq> optionalMemberReq = memberService.findUsername(username);

        if (optionalMemberReq.isPresent()) {
            return ResponseEntity.ok(optionalMemberReq.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> memberAdd(@RequestBody PostMemberReq postMemberReq){
        return ResponseEntity.ok(memberService.addMember(postMemberReq));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Integer> usernameRemove(@PathVariable String username) {
        return ResponseEntity.ok(memberService.deleteMember(username));
    }

    @PostMapping("/sign-in")
    public ResponseEntity memberDetais(@RequestBody PostMemberReq postMemberReq){

        ResponseEntity responseEntity = null;

        try {
            String token = memberService.signInMember(postMemberReq);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("username", postMemberReq.getUsername());
            responseBody.put("accessToken", "Bearer " + token);

            return ResponseEntity.status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body(responseBody);
        } catch (LoginFailedException exception) {

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 실패");
        }

        return responseEntity;
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            tokenBlacklistService.addToken(token);
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}

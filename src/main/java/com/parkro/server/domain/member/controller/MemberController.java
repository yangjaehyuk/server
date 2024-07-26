package com.parkro.server.domain.member.controller;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.member.service.TokenBlacklistService;
import com.parkro.server.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


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
    public ResponseEntity<String> usernameDetails(@RequestParam("user") String username) {

        memberService.findUsername(username);

        return ResponseEntity.ok("사용 가능한 아이디 입니다.");
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
    public ResponseEntity memberDetails(@RequestBody PostMemberReq postMemberReq){

        String token = memberService.signInMember(postMemberReq);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body("로그인 성공");
    }

}

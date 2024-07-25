package com.parkro.server.domain.member.controller;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원
 *
 * @author 김땡땡
 * @since 2024.07.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  김땡땡      최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @GetMapping
    public ResponseEntity<Integer> usernameDetails(@RequestParam("user") String username) {
        return ResponseEntity.ok(memberService.findUsername(username));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> memberAdd(@RequestBody PostMemberReq postMemberReq){
        return ResponseEntity.ok(memberService.addMember(postMemberReq));
    }
}

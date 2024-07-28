package com.parkro.server.domain.member.controller;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.SignInMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping()
    public ResponseEntity<String> usernameDetails(@RequestParam("user") String username) {

        memberService.findUsername(username);

        return ResponseEntity.ok("사용 가능한 아이디 입니다.");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> memberSignUp(@RequestBody PostMemberReq postMemberReq){

        return ResponseEntity.ok(memberService.addMember(postMemberReq));

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Integer> usernameRemove(@PathVariable String username) {

        return ResponseEntity.ok(memberService.removeMember(username));

    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> memberSignIn(@RequestBody PostMemberReq postMemberReq) {

        SignInMemberRes values = memberService.signInMember(postMemberReq);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + values.getToken());

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(values.getUsername());
    }


    @GetMapping("/{username}")
    public ResponseEntity<GetMemberRes> memberDetails(@PathVariable String username) {

        return ResponseEntity.ok(memberService.findMember(username));

    }

    @PutMapping("/{username}")
    public ResponseEntity memberModify(@PathVariable String username, @RequestBody PutMemberReq putMemberReq) {

        if (!username.equals(putMemberReq.getUsername())) {
            throw new CustomException(ErrorCode.FAIL_MODIFY_USER_DETIALS);
        }

        return ResponseEntity.ok(memberService.modifyMemberDetails(putMemberReq));

    }

}

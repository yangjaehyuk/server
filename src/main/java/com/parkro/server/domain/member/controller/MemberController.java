package com.parkro.server.domain.member.controller;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.PostMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


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
    @Validated
    public ResponseEntity memberSignUp(@Valid @RequestBody PostMemberReq postMemberReq, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                        } else {
                            return error.getDefaultMessage();
                        }
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }

        int memberId = memberService.addMember(postMemberReq);

        postMemberReq.setMemberId(memberId);

        memberService.modifyMemberName(postMemberReq);

        return ResponseEntity.ok("회원 가입이 완료되었습니다.");

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Integer> usernameRemove(@PathVariable String username) {

        return ResponseEntity.ok(memberService.removeMember(username));

    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> memberSignIn(@RequestBody PostMemberReq postMemberReq) {

        PostMemberRes values = memberService.signInMember(postMemberReq);

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
    @Validated
    public ResponseEntity<?> memberModify(@PathVariable String username, @Valid @RequestBody PutMemberReq putMemberReq, BindingResult bindingResult) {

        if (!username.equals(putMemberReq.getUsername())) {
            throw new CustomException(ErrorCode.FAIL_MODIFY_USER_DETIALS);
        }

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                        } else {
                            return error.getDefaultMessage();
                        }
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }


        return ResponseEntity.ok(memberService.modifyMemberDetails(putMemberReq));

    }

}

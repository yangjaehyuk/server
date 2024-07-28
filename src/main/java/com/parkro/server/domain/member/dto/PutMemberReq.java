package com.parkro.server.domain.member.dto;

import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PutMemberReq {

    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?& ]{8,12}$",
            message = "영문 대소문자, 숫자, 특수문자 포함 8 ~ 12자이어야 합니다.")
    private String password;
    
    @NotBlank(message = "닉네임은 필수 입력 값 입니다.")
    private String nickname;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    @Pattern(regexp = "\\d{3,}-\\d{4,}-\\d{4,}",
            message = "-를 붙여야 합니다.")
    private String phoneNumber;
}

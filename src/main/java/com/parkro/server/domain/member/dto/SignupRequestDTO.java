package com.parkro.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {
    private String username;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String carNumber;
}

package com.parkro.server.domain.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignInMemberRes {
    private String username;
    private String token;
}

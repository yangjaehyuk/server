package com.parkro.server.domain.member.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberReq {
    private String username;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String carNumber;
}

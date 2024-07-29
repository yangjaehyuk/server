package com.parkro.server.domain.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PutMemberReq {
    private String username;
    private String password;
    private String nickname;
    private String phoneNumber;
}

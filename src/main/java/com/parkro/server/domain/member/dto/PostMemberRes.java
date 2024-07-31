package com.parkro.server.domain.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostMemberRes {
    private String token;
    private String username;
}

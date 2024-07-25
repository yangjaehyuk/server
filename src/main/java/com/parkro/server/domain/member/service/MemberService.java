package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.SignupRequestDTO;

public interface MemberService {
    Integer findUsername(String username);
    Integer addMember(SignupRequestDTO signupRequestDTO);
}

package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.SignupRequestDTO;

public interface MemberService {
    Integer usernamefind(String username);
    Integer memberadd(SignupRequestDTO signupRequestDTO);
}

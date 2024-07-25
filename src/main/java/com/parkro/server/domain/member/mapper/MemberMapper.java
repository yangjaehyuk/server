package com.parkro.server.domain.member.mapper;

import com.parkro.server.domain.member.dto.SignupRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Integer usernameselect(String username);
    Integer memberinsert(SignupRequestDTO signupRequestDTO);
}

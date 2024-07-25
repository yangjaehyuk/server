package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.SignupRequestDTO;
import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Integer usernamefind(String username) {
        int cnt = memberMapper.usernameselect(username);
        if(cnt >= 1){
            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);
        }
        return cnt;
    }

    @Override
    @Transactional
    public Integer memberadd(SignupRequestDTO signupRequestDTO) {
        if (memberMapper.usernameselect(signupRequestDTO.getUsername()) < 0) {
            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);
        }

        return memberMapper.memberinsert(signupRequestDTO);
    }



}

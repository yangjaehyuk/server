package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public int checkMember(String username) {
        int cnt = memberMapper.countUsername(username);
        if(cnt >= 1){
            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);
        }
        return cnt;
    }
}

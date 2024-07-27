package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
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
    public Integer findUsername(String username) {

        int cnt = memberMapper.selectUsername(username);

        if(cnt >= 1){
            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);
        }
        return cnt;
    }

    @Override
    @Transactional
    public Integer addMember(PostMemberReq postMemberReq) {
        return memberMapper.insertMember(postMemberReq);
    }

    
    public GetMemberRes findMember(String username) {
        return memberMapper.selectUserByUsername(username);
    }
  
    @Override
    public Integer deleteMember(String username) {

        int cnt = memberMapper.deleteMember(username);

        if(cnt == 0){
            throw new CustomException(ErrorCode.FAIL_WITHDRAW);
        }

        return cnt;
    }

    @Override
    @Transactional
    public GetMemberRes findMemberByCarNumber(String carNumber) { return memberMapper.selectUserByCarNumber(carNumber);}
}

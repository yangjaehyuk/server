package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.SignInMemberRes;

import java.util.Optional;

public interface MemberService {
    Optional<PostMemberReq> findUsername(String username);
    Integer addMember(PostMemberReq postMemberReq);
    Integer deleteMember(String username);
    GetMemberRes findMember(String username);
    SignInMemberRes signInMember(PostMemberReq postMemberReq);
    GetMemberRes findMemberByCarNumber(String carNumber);
}

package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;

import java.util.Optional;

public interface MemberService {
    Optional<PostMemberReq> findUsername(String username);
    Integer addMember(PostMemberReq postMemberReq);
    Integer deleteMember(String username);
    GetMemberRes findMember(String username);
    String signInMember(PostMemberReq postMemberReq);
    GetMemberRes findMemberByCarNumber(String carNumber);
}

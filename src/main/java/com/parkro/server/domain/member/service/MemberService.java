package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;

public interface MemberService {
    Integer findUsername(String username);
    Integer addMember(PostMemberReq postMemberReq);
    GetMemberRes findMember(String username);
}

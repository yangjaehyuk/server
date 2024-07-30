package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.PostMemberRes;

public interface MemberService {
    void findUsername(String username);
    void addMember(PostMemberReq postMemberReq);
    Integer removeMember(String username);
    GetMemberRes findMember(String username);
    PostMemberRes signInMember(PostMemberReq postMemberReq);
    GetMemberRes findMemberByCarNumber(String carNumber);
    PutMemberReq modifyMemberDetails(PutMemberReq putMemberReq);
    void modifyCarNumber(PostMemberReq postMemberReq);
    void removeCarNumber(String username);
    void modifyFCM(PostMemberReq postMemberReq);
}

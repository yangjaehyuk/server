package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.PostMemberRes;

public interface MemberService {
    /* 아이디 중복 조회 */
    void findUsername(String username);
    /* 회원 가입 */
    void addMember(PostMemberReq postMemberReq);
    /* 회원 탈퇴 */
    Integer removeMember(String username);
    GetMemberRes findMember(String username);
    /* 로그인 */
    PostMemberRes signInMember(PostMemberReq postMemberReq);
    GetMemberRes findMemberByCarNumber(String carNumber);
    /* 회원 정보 수정 */
    PutMemberReq modifyMemberDetails(PutMemberReq putMemberReq);
    /* 차량 등록 */
    void modifyCarNumber(PostMemberReq postMemberReq);
    /* 차량 삭제 */
    void removeCarNumber(String username);
    /* FCM Token 업데이트 */
    void modifyFCM(PostMemberReq postMemberReq);
    /* 중복 차량 번호 조회 */
    boolean findCarNumber(String carNumber);
    /* 중복 전화 번호 조회 */
    boolean findPhoneNumber(String phoneNumber);
}

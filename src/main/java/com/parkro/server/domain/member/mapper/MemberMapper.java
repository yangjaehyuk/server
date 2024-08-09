package com.parkro.server.domain.member.mapper;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    // username(멤버 아이디)으로 중복 아이디 조회
    Optional<PostMemberReq> selectMemberName(String username);
    // PostMemberReq로 멤버 삽입
    void insertMember(PostMemberReq postMemberReq);
    // username(멤버 아이디)로 멤버 삭제
    Integer deleteMember(String username);
    // username(멤버 아이디)로 멤버 조회
    GetMemberRes selectMemberByUsername(String username);
    // CarNumber(차량 번호)로 멤버 조회
    GetMemberRes selectMemberByCarNumber(String carNumber);
    // PutMemberReq로 멤버 정보 수정
    Integer updateMemberDetails(PutMemberReq putMemberReq);
    // PostMemberReq로 차량 번호 수정
    Integer updateCarNumber(PostMemberReq postMemberReq);
    // username(멤버 아이디)로 차량 삭제
    void deleteCarNumber(String username);
    // PostMemberReq로 FCM토큰 업데이트
    Integer updateFCM(PostMemberReq postMemberReq);
    // carNumber(차량 번호)로 차량 중복 조회
    Integer countCarNumber(String carNumber);
    // phoneNumber(전화 번호)로 전화 번호 중복 조회
    Integer countPhoneNumber(String phoneNumber);
}

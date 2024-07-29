package com.parkro.server.domain.member.mapper;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostCarNumberReq;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<PostMemberReq> selectMemberName(String username);
    void insertMember(PostMemberReq postMemberReq);
    Integer deleteMember(String username);
    GetMemberRes selectMemberByUsername(String username);
    GetMemberRes selectMemberByCarNumber(String carNumber);
    Integer updateMemberDetails(PutMemberReq putMemberReq);
    void updateMemberName(PostMemberReq postMemberIdReq);
    Integer updateCarNumber(PostCarNumberReq postCarNumberReq);
}

package com.parkro.server.domain.member.mapper;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Integer selectUsername(String username);
    Integer insertMember(PostMemberReq postMemberReq);
    Integer deleteMember(String username);
    GetMemberRes selectUserByUsername(String username);
    GetMemberRes selectUserByCarNumber(String carNumber);
}

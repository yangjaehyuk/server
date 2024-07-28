package com.parkro.server.domain.member.mapper;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<PostMemberReq> selectUsername(String username);
    Integer insertUser(PostMemberReq postMemberReq);
    Integer deleteUser(String username);
    GetMemberRes selectUserByUsername(String username);
    GetMemberRes selectUserByCarNumber(String carNumber);
    Integer updateUserDetails(PutMemberReq putMemberReq);
}

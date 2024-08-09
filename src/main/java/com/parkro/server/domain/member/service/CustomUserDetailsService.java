package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;


    /**
     * username(멤버 아이디)를 기반으로 사용자 정보 불러오기
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberMapper.selectMemberName(String.valueOf(username))
                .map(this::addAuthorities)
                .orElseThrow(() -> new CustomException(ErrorCode.FIND_FAIL_USER_ID));
    }

    /**
     * 역할에 따른 권한 추가
     * @param postMemberReq
     * @return postMemberReq
     */
    private PostMemberReq addAuthorities(PostMemberReq postMemberReq) {
        postMemberReq.setAuthorities(List.of(new SimpleGrantedAuthority(postMemberReq.getRole())));

        return postMemberReq;
    }
}

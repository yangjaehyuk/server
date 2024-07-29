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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberMapper.selectMembername(String.valueOf(username))
                .map(this::addAuthorities)
                .orElseThrow(() -> new CustomException(ErrorCode.FIND_FAIL_USER_ID));
    }

    private PostMemberReq addAuthorities(PostMemberReq postMemberReq) {
        postMemberReq.setAuthorities(List.of(new SimpleGrantedAuthority(postMemberReq.getRole())));

        return postMemberReq;
    }
}

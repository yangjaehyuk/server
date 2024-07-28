package com.parkro.server.domain.member.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.SignInMemberRes;
import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import com.parkro.server.util.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Optional<PostMemberReq> findUsername(String username) {

        Optional<PostMemberReq> optionalPostMemberReq = memberMapper.selectUsername(username);

        if (optionalPostMemberReq.isPresent()) {

            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);


        } else {

            return Optional.empty();

        }
    }

    @Override
    @Transactional
    public Integer addMember(PostMemberReq postMemberReq) {

        String hashedPassword = passwordEncoder.encode(postMemberReq.getPassword());
        PostMemberReq signUpMemberReq = PostMemberReq.builder().username(postMemberReq.getUsername()).password(hashedPassword).nickname(postMemberReq.getNickname()).phoneNumber(postMemberReq.getPhoneNumber()).carNumber(postMemberReq.getCarNumber()).build();
        return memberMapper.insertUser(signUpMemberReq);

    }

    @Override
    @Transactional
    public SignInMemberRes signInMember(PostMemberReq postMemberReq) {
        Optional<PostMemberReq> memberOpt = memberMapper.selectUsername(postMemberReq.getUsername());

        if (memberOpt.isPresent()) {
            PostMemberReq member = memberOpt.get();

            if (passwordEncoder.matches(postMemberReq.getPassword(), member.getPassword())) {
                String token = jwtTokenProvider.createToken(member.getUsername(), Collections.singletonList(member.getRole()));
                return new SignInMemberRes(postMemberReq.getUsername(), token);
            }
        }

        throw new CustomException(ErrorCode.FAIL_SIGN_IN);
    }

    @Override
    public GetMemberRes findMember(String username) {
        return memberMapper.selectUserByUsername(username);
    }
  
    @Override
    public Integer removeMember(String username) {

        int cnt = memberMapper.deleteUser(username);

        if(cnt == 0){
            throw new CustomException(ErrorCode.FAIL_WITHDRAW);
        }

        return cnt;
    }


    @Override
    @Transactional(readOnly=true)
    public GetMemberRes findMemberByCarNumber(String carNumber) { return memberMapper.selectUserByCarNumber(carNumber);}

    @Override
    public PutMemberReq modifyMemberDetails(PutMemberReq putMemberReq) {

        String hashedPassword = passwordEncoder.encode(putMemberReq.getPassword());

        PutMemberReq modifiedReq = PutMemberReq.builder()
                .username(putMemberReq.getUsername())
                .password(hashedPassword)
                .nickname(putMemberReq.getNickname())
                .phoneNumber(putMemberReq.getPhoneNumber())
                .build();

        int cnt = memberMapper.updateUserDetails(modifiedReq);

        if (cnt == 0) {

            throw new CustomException(ErrorCode.FAIL_MODIFY_USER_DETIALS);

        }

        return putMemberReq;
    }
}

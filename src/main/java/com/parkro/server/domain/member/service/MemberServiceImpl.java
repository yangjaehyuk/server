package com.parkro.server.domain.member.service;

import com.parkro.server.domain.coupon.dto.PostMemberCouponReq;
import com.parkro.server.domain.coupon.service.CouponService;
import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.member.dto.PostSignInRes;
import com.parkro.server.domain.member.dto.PutMemberReq;
import com.parkro.server.domain.member.dto.PostMemberRes;
import com.parkro.server.domain.member.mapper.MemberMapper;
import com.parkro.server.domain.parking.mapper.ParkingMapper;
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
    private final ParkingMapper parkingMapper;
    private final CouponService couponService;

    @Transactional
    @Override
    public void findUsername(String username) {

        Optional<PostMemberReq> optionalPostMemberReq = memberMapper.selectMemberName(username);

        if (optionalPostMemberReq.isPresent()) {

            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);


        }
    }

    @Override
    @Transactional
    public void addMember(PostMemberReq postMemberReq) {

        boolean carNumberExists = findCarNumber(postMemberReq.getCarNumber());
        boolean phoneNumberExists = findPhoneNumber(postMemberReq.getPhoneNumber());

        if (carNumberExists && phoneNumberExists) {
            // 둘 다 존재하는 경우
            throw new CustomException(ErrorCode.CAR_NUMBER_AND_PHONE_NUMBER_ALREADY_EXISTS);
        } else if (carNumberExists) {
            // 차량 번호만 존재하는 경우
            throw new CustomException(ErrorCode.FIND_DUPLICATED_CARNUMBER);
        } else if (phoneNumberExists) {
            // 전화번호만 존재하는 경우
            throw new CustomException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
        }

        String hashedPassword = passwordEncoder.encode(postMemberReq.getPassword());

        postMemberReq.setPassword(hashedPassword);

        memberMapper.insertMember(postMemberReq);

        if(postMemberReq.getCarNumber() != null){

            parkingMapper.updateMemberId(postMemberReq);

        }

        GetMemberRes getMemberRes = findMember(postMemberReq.getUsername());
        long couponId = -1;
        couponId = couponService.findCouponIdByDate(getMemberRes.getCreatedDate());
        if(couponId == -1){
            throw new CustomException(ErrorCode.INVALID_COUPON_ID);
        }
        PostMemberCouponReq modifiedReq = PostMemberCouponReq.builder()
                .memberId(getMemberRes.getMemberId())
                .couponId(couponId)
                .build();
        couponService.addCoupons(modifiedReq);
    }


    @Override
    @Transactional
    public PostMemberRes signInMember(PostMemberReq postMemberReq) {

        GetMemberRes member = memberMapper.selectMemberByUsername(postMemberReq.getUsername());

        if (member == null) {
            throw new CustomException(ErrorCode.FAIL_SIGN_IN);
        }

        if (!passwordEncoder.matches(postMemberReq.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.FAIL_SIGN_IN);
        }

        String token = jwtTokenProvider.createToken(member.getUsername(), Collections.singletonList(member.getROLE()));

        PostSignInRes modifiedRes = PostSignInRes.builder()
                .username(member.getUsername())
                .carProfile(member.getProfileCar())
                .build();
        PostMemberRes postMemberRes = PostMemberRes.builder()
                .token(token)
                .postSignInRes(modifiedRes)
                .build();

        return postMemberRes;
    }


    @Override
    public GetMemberRes findMember(String username) {

        GetMemberRes member = memberMapper.selectMemberByUsername(username);
      
        if (member == null) {
            throw new CustomException(ErrorCode.FIND_FAIL_USER_ID);
        }
        return member;
    }
  
    @Override
    public Integer removeMember(String username) {
        GetMemberRes getMemberRes = findMember(username);

        couponService.removeCoupons(getMemberRes.getMemberId());
        int cnt = memberMapper.deleteMember(username);
        if(cnt == 0){
            throw new CustomException(ErrorCode.FAIL_WITHDRAW);
        }


        return cnt;
    }


    @Override
    @Transactional(readOnly=true)
    public GetMemberRes findMemberByCarNumber(String carNumber) { return memberMapper.selectMemberByCarNumber(carNumber);}

    @Override
    public PutMemberReq modifyMemberDetails(PutMemberReq putMemberReq) {


        String hashedPassword = passwordEncoder.encode(putMemberReq.getPassword());

        putMemberReq.setPassword(hashedPassword);

        int cnt = memberMapper.updateMemberDetails(putMemberReq);

        if (cnt == 0) {

            throw new CustomException(ErrorCode.FAIL_MODIFY_USER_DETIALS);

        }

        return putMemberReq;
    }

    @Override
    @Transactional
    public void modifyCarNumber(PostMemberReq postMemberReq) {

        int cnt = memberMapper.updateCarNumber(postMemberReq);

        if(cnt == 0){

            throw new CustomException(ErrorCode.FIND_DUPLICATED_CARNUMBER);

        }

        parkingMapper.updateMemberId(postMemberReq);

    }

    @Override
    @Transactional
    public void removeCarNumber(String username) {

        String carNumber = memberMapper.selectMemberByUsername(username).getCarNumber();

        if(carNumber == null){
            throw new CustomException(ErrorCode.INVALID_CAR_STATUS);
        }

        parkingMapper.deleteMemberId(carNumber);

        memberMapper.deleteCarNumber(username);

    }

    @Override
    public void modifyFCM(PostMemberReq postMemberReq) {
        PostMemberReq modifiedReq = PostMemberReq.builder()
                .fcmToken(postMemberReq.getFcmToken())
                .username(postMemberReq.getUsername())
                .build();
        int cnt = memberMapper.updateFCM(modifiedReq);
        if(cnt == 0){
            throw new CustomException(ErrorCode.FAIL_UPDATE_TOKEN);
        }
    }

    @Override
    public boolean findCarNumber(String carNumber) {
        int cnt = memberMapper.countCarNumber(carNumber);
        return cnt > 0;
    }

    @Override
    public boolean findPhoneNumber(String phoneNumber) {
        int cnt = memberMapper.countPhoneNumber(phoneNumber);
        return cnt > 0;
    }

}

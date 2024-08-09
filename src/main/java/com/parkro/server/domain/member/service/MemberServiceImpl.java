package com.parkro.server.domain.member.service;

import com.parkro.server.domain.alarm.service.AlarmService;
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
/**
 * 회원 정보 도메인
 *
 * @author 양재혁
 * @since 2024.07.25
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.25   양재혁      최초 생성
 * </pre>
 */
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
    private final AlarmService alarmService;

    /**
     * 아이디 중복 조회
     * @param username
     */
    @Transactional
    @Override
    public void findUsername(String username) {

        Optional<PostMemberReq> optionalPostMemberReq = memberMapper.selectMemberName(username);

        if (optionalPostMemberReq.isPresent()) {

            throw new CustomException(ErrorCode.FIND_DUPLICATED_USERNAME);


        }
    }

    /**
     * 회원 가입
     * @param postMemberReq
     */
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

    /**
     * 로그인
     * @param postMemberReq
     * @return 로그인 정보 {@link PostMemberRes}
     */
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

    /**
     * username 으로 멤버 정보 조회
     * @param username
     * @return 멤버 정보 {@link GetMemberRes}
     */
    @Override
    public GetMemberRes findMember(String username) {

        GetMemberRes member = memberMapper.selectMemberByUsername(username);
      
        if (member == null) {
            throw new CustomException(ErrorCode.FIND_FAIL_USER_ID);
        }
        return member;
    }

    /**
     * 회원 탈퇴
     * @param username
     * @return Integer 회원 탈퇴 성공 여부
     */
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

    /**
     * 차량번호로 멤버 정보 조회
     * @param carNumber
     * @return GetMemberRes
     */
    @Override
    @Transactional(readOnly=true)
    public GetMemberRes findMemberByCarNumber(String carNumber) { return memberMapper.selectMemberByCarNumber(carNumber);}

    /**
     * 회원 정보 수정
     * @param putMemberReq
     * @return 회원 수정 정보 {@link PutMemberReq}
     */
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

    /**
     * 차량 등록
     * @param postMemberReq
     */
    @Override
    @Transactional
    public void modifyCarNumber(PostMemberReq postMemberReq) {

        int cnt = memberMapper.updateCarNumber(postMemberReq);

        if(cnt == 0){

            throw new CustomException(ErrorCode.FIND_DUPLICATED_CARNUMBER);

        }

        parkingMapper.updateMemberId(postMemberReq);

    }

    /**
     * 차량 삭제
     * @param username
     */
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

    /**
     * FCM Token 업데이트
     * @param postMemberReq
     */
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

        // 주제 구독
        alarmService.subscribeToTopic(postMemberReq.getFcmToken(), "allUsers");
    }

    /**
     * 중복 차량 번호 조회
     * @param carNumber
     * @return Boolean 중복 여부
     */
    @Override
    public boolean findCarNumber(String carNumber) {
        int cnt = memberMapper.countCarNumber(carNumber);
        return cnt > 0;
    }

    /**
     * 중복 전화 번호 조회
     * @param phoneNumber
     * @return Boolean 중복 여부
     */
    @Override
    public boolean findPhoneNumber(String phoneNumber) {
        int cnt = memberMapper.countPhoneNumber(phoneNumber);
        return cnt > 0;
    }

}

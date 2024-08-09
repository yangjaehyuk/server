package com.parkro.server.domain.coupon.service;

import com.parkro.server.domain.coupon.dto.PostMemberCouponReq;
import com.parkro.server.domain.coupon.mapper.CouponMapper;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
/**
 * 쿠폰 도메인
 *
 * @author 양재혁
 * @since 2024.08.03
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  양재혁       최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class CouponServiceImpl implements CouponService {
    private final CouponMapper couponMapper;

    /**
     * 날짜 기준 쿠폰(coupon_id) 목록 조회
     * @param date
     * @return Integer 쿠폰 아이디
     */
    @Override
    @Transactional(readOnly = true)
    public Integer findCouponIdByDate(Date date) {

        int couponId = 0;
        couponId = couponMapper.selectCouponIdByDate(date);

        if(couponId == 0) {
            throw new CustomException(ErrorCode.INVALID_COUPON_ID);
        }
        return couponId;
    }

    /**
     * 쿠폰 등록
     * @param postMemberCouponReq
     */
    @Override
    @Transactional
    public void addCoupons(PostMemberCouponReq postMemberCouponReq) {
        PostMemberCouponReq modifiedMemberCouponReq = PostMemberCouponReq.builder()
                .memberId(postMemberCouponReq.getMemberId())
                .couponId(postMemberCouponReq.getCouponId())
                .build();

        couponMapper.insertCoupons(modifiedMemberCouponReq);
    }

    /**
     * 쿠폰 삭제
     * @param memberId
     */
    @Override
    @Transactional
    public void removeCoupons(Integer memberId) {
        couponMapper.deleteCoupons(memberId);
    }

    /**
     * 쿠폰 상태 업데이트 (ACTIVE -> USED)
     * @param memberCouponId
     */
    @Override
    @Transactional
    public void modifyCouponStatusUse(Integer memberCouponId) {
        couponMapper.updateCouponStatus(memberCouponId);
    }
}

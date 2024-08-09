package com.parkro.server.domain.coupon.service;

import com.parkro.server.domain.coupon.dto.PostMemberCouponReq;

import java.util.Date;

public interface CouponService {

  /* 날짜 기준 쿠폰(coupon_id) 목록 조회 */
  Integer findCouponIdByDate(Date date);

  /* 쿠폰 등록 */
  void addCoupons(PostMemberCouponReq postMemberCouponReq);

  /* 쿠폰 삭제 */
  void removeCoupons(Integer memberId);

  /* 사용한 쿠폰 상태 업데이트 (ACTIVE -> USED) */
  void modifyCouponStatusUse(Integer memberCouponId);
}

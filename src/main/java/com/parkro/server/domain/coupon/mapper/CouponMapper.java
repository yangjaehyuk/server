package com.parkro.server.domain.coupon.mapper;

import com.parkro.server.domain.coupon.dto.PostMemberCouponReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface CouponMapper {

  // 날짜 기준 쿠폰(coupon_id) 목록 조회
  Integer selectCouponIdByDate(Date date);

  // 쿠폰 등록
  void insertCoupons(PostMemberCouponReq postMemberCouponReq);

  // 쿠폰 삭제
  Integer deleteCoupons(Integer memberId);

  // 사용한 쿠폰 상태 업데이트 (ACTIVE -> USED)
  Integer updateCouponStatus(Integer memberCouponId);
}

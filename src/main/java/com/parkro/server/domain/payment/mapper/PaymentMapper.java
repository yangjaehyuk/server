package com.parkro.server.domain.payment.mapper;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.GetPaymentRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

  // 유저가 보유한 쿠폰 중 유효한 쿠폰 조회
  List<GetPaymentCouponRes> selectMemberCoupons(Integer memberId);

  // 결제 정보 삽입
  void insertPayment(@Param("req") PostPaymentReq req, @Param("memberId") Integer memberId);

  // parkingId로 결제 정보 조회
  GetPaymentRes selectPaymentByParkingId(Integer parkingId);

  // 결제 취소 상태로 업데이트
  void updateCancelledDate(Integer paymentId);
}

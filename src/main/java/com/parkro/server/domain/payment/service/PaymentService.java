package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.GetPaymentRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;

import java.util.List;

public interface PaymentService {

  // 사용자 보유 쿠폰 조회
  List<GetPaymentCouponRes> findPaymentCoupon(String username);

  // 주차장 결제 내역 등록
  Integer addPayment(PostPaymentReq req);

  // 정산(결제) 상세 정보 조회
  GetPaymentRes findPaymentByParkingId(Integer parkingId);
}

package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;

import java.util.List;

public interface PaymentService {

  List<GetPaymentCouponRes> findPaymentCoupon(String username);
  Integer addPayment(PostPaymentReq req);
}
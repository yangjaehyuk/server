package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;

import java.util.List;

public interface PaymentService {

  List<GetPaymentCouponRes> findPaymentCoupon(String username);
}
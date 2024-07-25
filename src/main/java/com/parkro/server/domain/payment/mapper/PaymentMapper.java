package com.parkro.server.domain.payment.mapper;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

  List<GetPaymentCouponRes> selectMemberCoupons(Integer memberId);
  void insertPayment(PostPaymentReq req);
}

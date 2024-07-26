package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final MemberService memberService;
  private final PaymentMapper paymentMapper;

  @Override
  public List<GetPaymentCouponRes> findPaymentCoupon(String username) {
    GetMemberRes member = memberService.findMember(username);
    return paymentMapper.selectMemberCoupons(member.getMemberId());
  }
}

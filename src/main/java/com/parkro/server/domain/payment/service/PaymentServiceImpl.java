package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.GetPaymentRes;
import com.parkro.server.domain.payment.mapper.PaymentMapper;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {

  private final MemberService memberService;
  private final PaymentMapper paymentMapper;

  @Override
  @Transactional(readOnly = true)
  public List<GetPaymentCouponRes> findPaymentCoupon(String username) {
    GetMemberRes member = memberService.findMember(username);
    return paymentMapper.selectMemberCoupons(member.getMemberId());
  }

  @Override
  @Transactional
  public Integer addPayment(PostPaymentReq req) {
    GetMemberRes member = memberService.findMember(req.getUsername());
    paymentMapper.insertPayment(req, member.getMemberId());
    return req.getPaymentId();
  }

  /**
   * 정산(결제) 상세 정보 조회
   * @param parkingId
   * @return 주차 정산(걸제) 후 상세 내역 조회
   */
  @Override
  @Transactional(readOnly = true)
  public GetPaymentRes findPaymentByParkingId(Integer parkingId) {
    return paymentMapper.selectPaymentByParkingId(parkingId);
  }
}

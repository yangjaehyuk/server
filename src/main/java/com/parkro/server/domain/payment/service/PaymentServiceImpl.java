package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.parking.service.ParkingService;
import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.mapper.PaymentMapper;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.parkro.server.exception.ErrorCode.INVALID_PAYMENT_CANCELLATION;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {

  private final MemberService memberService;
  private final PaymentSchedulerService paymentSchedulerService;
  private final PaymentMapper paymentMapper;

  /**
   * 사용자 보유 쿠폰 조회
   * @param username
   * @return 사용자가 보유한 쿠폰 리스트 {@link List<GetPaymentCouponRes>}
   */
  @Override
  public List<GetPaymentCouponRes> findPaymentCoupon(String username) {
    GetMemberRes member = memberService.findMember(username);
    return paymentMapper.selectMemberCoupons(member.getMemberId());
  }

  /**
   * 주차장 결제 내역 등록
   * @param req
   * @return 정상적으로 등록되었다면 반환하는 paymentId
   */
  @Override
  @Transactional
  public Integer addPayment(PostPaymentReq req) {
    GetMemberRes member = memberService.findMember(req.getUsername());
    paymentMapper.insertPayment(req, member.getMemberId());
    // 결제 취소 스케쥴러 호출
    paymentSchedulerService.schedulerModifyCancelledDate(req.getParkingId(), req.getPaymentId());
    return req.getPaymentId();
  }
}

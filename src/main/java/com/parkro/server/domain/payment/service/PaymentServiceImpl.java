package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.coupon.service.CouponService;
import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.parking.service.ParkingService;
import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.GetPaymentRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import com.parkro.server.domain.payment.mapper.PaymentMapper;
import com.parkro.server.domain.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 결제 내역 등록, 조회와 같은 주차 사전 정산 관련 로직
 *
 * @author 김지수
 * @since 2024.7.26
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.26   김지수      최초 생성
 * 2024.07.26   김지수      사용자 보유 쿠폰 조회 API
 * 2024.07.26   김지수      주차장 결제 내역 등록 API
 * 2024.07.28   김지수      정산(결제) 상세 정보 조회 API
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {

  private final MemberService memberService;
  private final PaymentSchedulerService paymentSchedulerService;
  private final PaymentMapper paymentMapper;
  private final ParkingService parkingService;
  private final CouponService couponService;
  private final ReceiptService receiptService;

  /**
   * 사용자 보유 쿠폰 조회
   *
   * @param username
   * @return 사용자가 보유한 쿠폰 리스트 {@link List<GetPaymentCouponRes>}
   */
  @Override
  @Transactional(readOnly = true)
  public List<GetPaymentCouponRes> findPaymentCoupon(String username) {
    GetMemberRes member = memberService.findMember(username);
    return paymentMapper.selectMemberCoupons(member.getMemberId());
  }

  /**
   * 주차장 결제 내역 등록
   *
   * @param req
   * @return 정상적으로 등록되었다면 반환하는 paymentId
   */
  @Override
  @Transactional
  public Integer addPayment(PostPaymentReq req) {
    GetMemberRes member = memberService.findMember(req.getUsername());
    paymentMapper.insertPayment(req, member.getMemberId());

    // 쿠폰 사용 상태로 변경
    if (req.getMemberCouponId() != null) couponService.modifyCouponStatusUse(req.getMemberCouponId());

    // 영수증 사용 상태로 변경
    if (req.getReceiptId() != null) receiptService.modifyReceiptStatus(req.getReceiptId());

    // 주차 상태 변경 (입차 -> 결제)
    parkingService.modifyParkingStatusPay(req.getParkingId());
    // 결제 취소 스케쥴러 호출
    paymentSchedulerService.schedulerModifyCancelledDate(req.getParkingId(), req.getPaymentId(), member.getFcmToken());
    return req.getPaymentId();
  }

  /**
   * 정산(결제) 상세 정보 조회
   *
   * @param parkingId
   * @return 주차 정산(걸제) 후 상세 내역 조회
   */
  @Override
  @Transactional(readOnly = true)
  public GetPaymentRes findPaymentByParkingId(Integer parkingId) {
    return paymentMapper.selectPaymentByParkingId(parkingId);
  }
}

package com.parkro.server.domain.payment.controller;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import com.parkro.server.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 정산
 *
 * @author 김지수
 * @since 2024.07.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  김지수      최초 생성
 * 2024.07.26  김지수      쿠폰 조회 API
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

  private final PaymentService paymentService;

  @GetMapping("/coupon/{username}")
  public ResponseEntity<List<GetPaymentCouponRes>> paymentCouponList(@PathVariable String username) {
    return ResponseEntity.ok(paymentService.findPaymentCoupon(username));
  }

  @PostMapping
  public ResponseEntity<Integer> paymentAdd(@RequestBody PostPaymentReq req) {
    return ResponseEntity.ok(paymentService.addPayment(req));
  }
}

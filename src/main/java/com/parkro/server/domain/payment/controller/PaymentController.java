package com.parkro.server.domain.payment.controller;

import com.parkro.server.domain.payment.dto.GetPaymentCouponRes;
import com.parkro.server.domain.payment.dto.GetPaymentRes;
import com.parkro.server.domain.payment.dto.PostPaymentReq;
import com.parkro.server.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
@Log4j2
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

  @GetMapping
  public ResponseEntity<GetPaymentRes> paymentDetails(@RequestParam("parking") Integer parkingId) {
    return ResponseEntity.ok(paymentService.findPaymentByParkingId(parkingId));
  }

  @GetMapping("/success")
  public ResponseEntity<String> paymentSuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam String amount) {

    // 결제 승인 API 호출
    log.info("[Payment success] paymentKey: {}, orderId: {}, amount: {}", paymentKey, orderId, amount);

    // 여기서 결제 승인 로직을 처리 및 결제 내역을 저장

    HttpHeaders headers = new HttpHeaders();

    // 안드로이드로 리다이렉트
    String redirectUrl = String.format("parkro://payment/success?orderId=%s&amount=%s&paymentKey=%s", orderId, amount, paymentKey);
    headers.setLocation(URI.create(redirectUrl));
    return new ResponseEntity<>(headers, HttpStatus.FOUND);
  }
}

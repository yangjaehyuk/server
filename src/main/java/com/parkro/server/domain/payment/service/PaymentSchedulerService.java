package com.parkro.server.domain.payment.service;

import java.util.concurrent.CompletableFuture;

public interface PaymentSchedulerService {

  // 호출되고 지정한 시간(10분)이 지나면 특정 작업(결제 취소) 호출
  CompletableFuture<Void> schedulerModifyCancelledDate(Integer parkingId, Integer paymentId, String fcmToken);

  // 결제 취소
  void modifyCancelledDate(Integer parkingId, Integer paymentId, String fcmToken);
}

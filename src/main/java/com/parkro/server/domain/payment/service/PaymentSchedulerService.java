package com.parkro.server.domain.payment.service;

import java.util.concurrent.CompletableFuture;

public interface PaymentSchedulerService {

  CompletableFuture<Void> schedulerModifyCancelledDate(Integer parkingId, Integer paymentId);
  void modifyCancelledDate(Integer parkingId, Integer paymentId);
}

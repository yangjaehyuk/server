package com.parkro.server.domain.payment.service;

import com.parkro.server.domain.parking.service.ParkingService;
import com.parkro.server.domain.payment.mapper.PaymentMapper;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.parkro.server.exception.ErrorCode.INVALID_PAYMENT_CANCELLATION;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentSchedulerServiceImpl implements PaymentSchedulerService {

  private final ParkingService parkingService;
  private final PaymentMapper paymentMapper;

  /**
   * 비동기로 수행하는 결제 취소 스케쥴러 호출 메서드
   * @param parkingId
   * @param paymentId
   * @return 작업 완료를 나타내는 {@link CompletableFuture<Void>}
   */
  @Async
  @Override
  public CompletableFuture<Void> schedulerModifyCancelledDate(Integer parkingId, Integer paymentId) {
    log.info("결제 취소 카운트 시작");
    try {
      TimeUnit.MINUTES.sleep(10);
      modifyCancelledDate(parkingId, paymentId);
    } catch (InterruptedException e) {
      log.error("Error during sleep: " + e);
    }

    return CompletableFuture.completedFuture(null);
  }

  /**
   * 결제 취소
   * @param parkingId
   * @param paymentId
   */
  @Override
  public void modifyCancelledDate(Integer parkingId, Integer paymentId) {
    // 현재 출차한 상태라면 결제 취소 로직 수행하지 않음
    if (parkingService.findParkingByParkingId(parkingId).getStatus().equals("EXIT")) return;
    // 현재 결제 전 상태라면 예외 처리
    if (parkingService.findParkingByParkingId(parkingId).getStatus().equals("ENTRANCE")) throw new CustomException(INVALID_PAYMENT_CANCELLATION);

    // 결제 취소 수행
    paymentMapper.updateCancelledDate(paymentId);
    // 결제 취소 로직

    log.info("{}번 결제 내역 취소 완료", paymentId);
  }
}

package com.parkro.server.domain.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 알림 스케줄러
 *
 * @author 김민정
 * @since 2024.08.06
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.06  김민정      최초 생성
 * 2024.08.06  김민정      쿠폰 알림 스케줄러
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class AlarmSchedulerService {
  private final AlarmService alarmService;

  // cron = 초, 분, 시, 일, 월, 요일, 연도
  @Scheduled(cron = "0 0 10 1 * ?")  // 매월 1일 오전 10시 실행
//    @Scheduled(initialDelay = 10000, fixedDelay = Long.MAX_VALUE) // 즉시 실행
  public void sendCouponNotification() {
    String topic = "allUsers";
    alarmService.sendCouponIssueNotification(topic);
  }
}

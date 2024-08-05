package com.parkro.server.domain.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

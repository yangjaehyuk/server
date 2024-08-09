package com.parkro.server.domain.alarm.service;

import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;

public interface AlarmService {
    // 입차 알림
    void sendParkingEntryNotification(ParkingEntryDTO dto);
    // 결제 취소 알림
    void sendPayCancelNotification(String fcmToken);
    // 유저를 topic에 구독시키기
    void subscribeToTopic(String fcmToken, String topic);
    // 쿠폰 발급 알림
    void sendCouponIssueNotification(String topic);
}

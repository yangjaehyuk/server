package com.parkro.server.domain.alarm.service;

import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;

public interface AlarmService {

    void sendParkingEntryNotification(ParkingEntryDTO dto);
    void sendPayCancelNotification(String fcmToken);
    void subscribeToTopic(String fcmToken, String topic);
    void sendCouponIssueNotification(String topic);
}

package com.parkro.server.domain.alarm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.parkro.server.exception.ErrorCode.FCM_TOKEN_NOT_VALID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmServiceImpl implements AlarmService {

    private final FirebaseMessaging firebaseMessaging;

    // 입차 알림
    @Override
    public void sendParkingEntryNotification(ParkingEntryDTO dto) {
        String title = "입차 알림";
        String body = dto.getCarNumber() + " 차량이 입차했습니다.";
        sendNotification(dto.getFcmToken(), title, body);

    }

    // 결제 취소 알림
    @Override
    public void sendPayCancelNotification(String token) {
        String title = "주차 정산 취소 알림";
        String body = "정산 후, 10분 이내에 출차하지 않아 결제가 취소됩니다.";
        sendNotification(token, title, body);
    }

    // 알림 전송
    private void sendNotification(String token, String title, String body) {

        if (token == null || token.isEmpty()) {
            log.error("FCM 토큰이 유효하지 않습니다.");
            throw new CustomException(FCM_TOKEN_NOT_VALID);
        }

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        firebaseMessaging.sendAsync(message);
    }

}

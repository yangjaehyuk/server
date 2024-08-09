package com.parkro.server.domain.alarm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.parkro.server.domain.alarm.dto.AlarmDTO;
import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.parkro.server.exception.ErrorCode.FCM_TOKEN_NOT_VALID;

/**
 * 알림
 *
 * @author 김민정
 * @since 2024.08.06
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  김민정      최초 생성
 * 2024.07.30  김민정      공통 알림 전송 메서드
 * 2024.07.30  김민정      입차 알림
 * 2024.07.30  김민정      결제 취소 알림
 * 2024.08.05  김민정      토픽 기반 공통 알림 전송 메서드
 * 2024.08.05  김민정      유저를 topic에 구독시키기
 * 2024.08.05  김민정      쿠폰 발급 알림
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmServiceImpl implements AlarmService {

    private final FirebaseMessaging firebaseMessaging;

    // 공통 알림 전송 메서드
    private void sendNotification(String token, AlarmDTO alarmDTO) {

        if (token == null || token.isEmpty()) {
            log.error("FCM 토큰이 유효하지 않습니다.");
            throw new CustomException(FCM_TOKEN_NOT_VALID);
        }

        Notification notification = Notification.builder()
                .setTitle(alarmDTO.getTitle())
                .setBody(alarmDTO.getBody())
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        firebaseMessaging.sendAsync(message);
    }

    // 토픽 기반 공통 알림 전송 메서드
    private void sendNotificationByTopic(String topic, AlarmDTO alarmDTO) {

        Notification notification = Notification.builder()
                .setTitle(alarmDTO.getTitle())
                .setBody(alarmDTO.getBody())
                .build();

        Message message = Message.builder()
                .setTopic(topic)
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            log.info("모든 유저에게 쿠폰 발급 알림을 전송했습니다.");
        } catch (Exception e) {
            log.error("쿠폰 발급 알림 전송 실패: ", e);
        }
    }

    // 유저를 topic에 구독시키기
    @Override
    public void subscribeToTopic(String fcmToken, String topic) {

        if (fcmToken == null || fcmToken.isEmpty()) {
            log.error("FCM 토큰이 유효하지 않습니다.");
            return;
        }

        try {
            firebaseMessaging.subscribeToTopic(List.of(fcmToken), topic);
        } catch (Exception e) {
            log.error("토픽에 FCM 토큰 등록을 실패했습니다.", e);
        }
    }

    // 입차 알림
    @Override
    public void sendParkingEntryNotification(ParkingEntryDTO dto) {

        AlarmDTO alarmDTO = AlarmDTO.builder()
                .title("입차 알림")
                .body(dto.getCarNumber() + " 차량이 입차했습니다.")
                .build();
        sendNotification(dto.getFcmToken(), alarmDTO);
    }

    // 결제 취소 알림
    @Override
    public void sendPayCancelNotification(String token) {

        AlarmDTO alarmDTO = AlarmDTO.builder()
                .title("주차 정산 취소 알림")
                .body("정산 후, 10분 이내에 출차하지 않아 결제가 취소됩니다.")
                .build();
        sendNotification(token, alarmDTO);
    }

    // 쿠폰 발급 알림
    @Override
    public void sendCouponIssueNotification(String topic) {

        AlarmDTO alarmDTO = AlarmDTO.builder()
                .title("쿠폰 발급 알림")
                .body("주차 할인 쿠폰이 발급되었습니다.")
                .build();
        sendNotificationByTopic(topic, alarmDTO);
    }
}

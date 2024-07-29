package com.parkro.server.domain.alarm.event;

import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;
import com.parkro.server.domain.alarm.dto.PaymentCancelDTO;
import com.parkro.server.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AlarmEventListener {

    private final AlarmService alarmService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleParkingEntryEvent(ParkingEntryDTO event) {
        alarmService.sendParkingEntryNotification(event);
    }

//    @EventListener: 결제 취소 트랜잭션이 완전히 끝나지 않았을 경우, 알림을 테스트해보기 위한 용도
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePayCancelEvent(PaymentCancelDTO event) {
        alarmService.sendPayCancelNotification(event.getFcmToken());
    }
}

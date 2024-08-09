package com.parkro.server.domain.alarm.event;

import com.parkro.server.domain.alarm.dto.ParkingEntryDTO;
import com.parkro.server.domain.alarm.dto.PaymentCancelDTO;
import com.parkro.server.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

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
 * 2024.07.30  김민정      입차 완료 이벤트 리스너
 * 2024.07.30  김민정      결제 취소 완료 이벤트 리스너
 * </pre>
 */
@Component
@RequiredArgsConstructor
public class AlarmEventListener {

    private final AlarmService alarmService;

    // 입차 완료 이벤트 리스너
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleParkingEntryEvent(ParkingEntryDTO event) {
        alarmService.sendParkingEntryNotification(event);
    }


    // 결제 취소 완료 이벤트 리스너
//    @EventListener: 결제 취소 트랜잭션이 완전히 끝나지 않았을 경우, 알림을 테스트해보기 위한 용도
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePayCancelEvent(PaymentCancelDTO event) {
        alarmService.sendPayCancelNotification(event.getFcmToken());
    }
}

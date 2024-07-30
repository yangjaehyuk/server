package com.parkro.server.domain.alarm.controller;

import com.parkro.server.domain.alarm.service.AlarmService;
import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.payment.service.PaymentSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FCM 알림
 *
 * @author 김민정
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  김민정      최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final MemberService memberService;
    private final AlarmService alarmService;
    private final PaymentSchedulerService paymentSchedulerService;

    // 결제 취소 알림 테스트
    @PostMapping("/pay-cancel")
    public ResponseEntity<String> sendPayCancelNotification(@RequestParam("username") String username) {

        GetMemberRes member = memberService.findMember(username);
        alarmService.sendPayCancelNotification(member.getFcmToken());
        return ResponseEntity.ok("결제 취소 알림이 전송되었습니다.");
    }

    // 결제 취소 알림 테스트 - 스케줄러 실행
    @PostMapping("/pay-cancel/scheduler")
    public ResponseEntity<String> sendPayCancelNotificationByScheduler(@RequestParam("username") String username,
                                                                       @RequestParam("parkingId") Integer parkingId,
                                                                       @RequestParam("paymentId") Integer paymentId) {
        GetMemberRes member = memberService.findMember(username);
        paymentSchedulerService.modifyCancelledDate(parkingId, paymentId, member.getFcmToken());
        return ResponseEntity.ok("결제 취소 알림이 전송되었습니다.");
    }
}

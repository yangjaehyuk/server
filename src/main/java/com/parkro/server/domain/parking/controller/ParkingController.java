package com.parkro.server.domain.parking.controller;

import com.parkro.server.domain.parking.dto.PostParkingReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import com.parkro.server.domain.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 주차
 *
 * @author 김민정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  김민정      최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;


    // 입차
    @PostMapping("/in")
    public ResponseEntity<Integer> parkingAdd(@RequestBody PostParkingReq req) {
        return ResponseEntity.ok(parkingService.addParking(req));

    }

    // 주차 정산(전) 정보 조회
    @GetMapping
    public ResponseEntity<List<GetParkingPayRes>> parkingPayDetails(@RequestParam String username) {
        return ResponseEntity.ok(parkingService.findParkingPay(username));
    }
}

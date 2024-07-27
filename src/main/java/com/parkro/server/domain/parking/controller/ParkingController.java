package com.parkro.server.domain.parking.controller;

import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

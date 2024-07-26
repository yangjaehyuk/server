package com.parkro.server.domain.parkinglot.controller;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;
import com.parkro.server.domain.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 주차장
 *
 * @author 김민정
 * @since 2024.07.25
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
@RequestMapping("/parking-lot")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    // 백화점 지점별 외부 주차장 조회
    @GetMapping
    public ResponseEntity<List<GetParkingLotRes>> parkingLotList(@RequestParam Integer store) {
        return ResponseEntity.ok(parkingLotService.findParkingLots(store));
    }

}

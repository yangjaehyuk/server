package com.parkro.server.domain.parkinglot.service;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;

import java.util.List;

/**
 * 주차장
 *
 * @author 김민정
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  김민정      최초 생성
 * 2024.07.26  김민정      지점별 외부 주차장 목록 조회
 * 2024.08.06  김민정      주차장 잔여 좌석 수 조절
 * </pre>
 */
public interface ParkingLotService {
    // 지점별 외부 주차장 목록 조회
    List<GetParkingLotRes> findParkingLots(Integer storeId);
    // 주차장 잔여 좌석 수 조절
    int modifyUsedSpaces(Integer parkingLotId, Integer cnt);
}

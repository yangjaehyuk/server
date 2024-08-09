package com.parkro.server.domain.parkinglot.service;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;
import com.parkro.server.domain.parkinglot.mapper.ParkingLotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

  private final ParkingLotMapper parkingLotMapper;

  // 지점별 외부 주차장 목록 조회
  @Override
  @Transactional
  public List<GetParkingLotRes> findParkingLots(Integer storeId) {
    return parkingLotMapper.selectParkingLots(storeId);
  }

  // 주차장 잔여 좌석 수 조절
  @Override
  @Transactional
  public int modifyUsedSpaces(Integer parkingLotId, Integer cnt) {
    return parkingLotMapper.updateUsedSpaces(parkingLotId, cnt);
  }
}

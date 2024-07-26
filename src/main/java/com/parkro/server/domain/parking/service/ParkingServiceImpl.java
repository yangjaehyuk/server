package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.mapper.ParkingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

  private final ParkingMapper parkingMapper;

  /**
   * 주차 정보 조회
   * @param parkingId
   * @return
   */
  @Override
  public GetParkingRes findParkingByParkingId(Integer parkingId) {
    return parkingMapper.selectParkingByParkingId(parkingId);
  }
}

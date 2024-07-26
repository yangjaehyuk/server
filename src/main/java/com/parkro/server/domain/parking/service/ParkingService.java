package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingRes;

public interface ParkingService {

  GetParkingRes findParkingByParkingId(Integer parkingId);
}

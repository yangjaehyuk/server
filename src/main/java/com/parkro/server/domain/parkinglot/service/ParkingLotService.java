package com.parkro.server.domain.parkinglot.service;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;

import java.util.List;

public interface ParkingLotService {
    List<GetParkingLotRes> findParkingLots(Integer storeId);
}

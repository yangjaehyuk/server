package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingPayRes;

import java.util.List;

public interface ParkingService {
    List<GetParkingPayRes> findParkingPay(String username);
}

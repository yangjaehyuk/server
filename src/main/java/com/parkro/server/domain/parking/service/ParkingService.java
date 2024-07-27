package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.PostParkingReq;

public interface ParkingService {

    // 입차
    Integer addParking(PostParkingReq req);
}

package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;

public interface ParkingService {

  GetParkingRes findParkingByParkingId(Integer parkingId);
  Integer modifyParkingStatus(Integer parkingId);
  Integer addParking(PostParkingReq req);
  List<GetParkingPayRes> findParkingPay(String username);
}

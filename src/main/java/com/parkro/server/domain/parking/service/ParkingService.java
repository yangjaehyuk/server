package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;

import java.util.List;

public interface ParkingService {

  GetParkingRes findParkingByParkingId(Integer parkingId);
  Integer modifyParkingStatus(Integer parkingId);
  
    // 입차
    Integer addParking(PostParkingReq req);

    // 출차
    Integer modifyParkingOut(PatchParkingReq req);

    // 주차 정산(전) 정보 조회
    List<GetParkingPayRes> findParkingPay(String username);

    // 나의 주차 내역 목록 조회
    List<GetParkingRes> findMyParkingList(String username);

    // 주차 내역 삭제
    Integer removeParking(Integer parkingId);
}

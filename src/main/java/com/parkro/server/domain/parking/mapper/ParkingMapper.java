package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingMapper {

  // parkingId로 주차 정보 조회
  GetParkingRes selectParkingByParkingId(Integer parkingId);
  
  // 결제 취소 후 parking status 업데이트
  Integer updateParkingStatus(Integer parkingId);
  
    // 입차
    Integer insertParking(PostParkingReq req);

    // 주차 정산(전) 정보 조회
    List<GetParkingPayRes> selectParkingPay(Integer memberId);
}

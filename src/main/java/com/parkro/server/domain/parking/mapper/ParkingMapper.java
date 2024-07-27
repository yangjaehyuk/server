package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingMapper {

    // 주차 정산(전) 정보 조회
    List<GetParkingPayRes> selectParkingPay(Integer memberId);
}

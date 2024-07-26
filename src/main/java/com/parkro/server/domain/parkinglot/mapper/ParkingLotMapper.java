package com.parkro.server.domain.parkinglot.mapper;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingLotMapper {
    // 지전별 외부 주차장 목록 조회
    List<GetParkingLotRes> selectParkingLots(Integer storeId);
}

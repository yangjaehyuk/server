package com.parkro.server.domain.parkinglot.mapper;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParkingLotMapper {
    // 지점별 외부 주차장 목록 조회
    List<GetParkingLotRes> selectParkingLots(Integer storeId);

    // 주차장 잔여 좌석 수 조절
    int updateUsedSpaces(@Param("parkingLotId")Integer parkingLotId,
                         @Param("cnt")Integer cnt);
}

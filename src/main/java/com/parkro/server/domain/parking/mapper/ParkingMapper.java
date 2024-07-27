package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingMapper {

    // 입차
    void insertParking(PostParkingReq req);

    // 출차
    Integer updateParkingOut(PatchParkingReq req);
}

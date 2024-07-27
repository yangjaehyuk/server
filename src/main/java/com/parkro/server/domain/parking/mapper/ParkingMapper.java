package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.parking.dto.PostParkingReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingMapper {

    // 입차
    Integer insertParking(PostParkingReq req);
}

package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingMapper {

    // 입차
    void insertParking(PostParkingReq req);

    // 출차
    Integer updateParkingOut(PatchParkingReq req);

    // 주차 정산(전) 정보 조회
    List<GetParkingPayRes> selectParkingPay(Integer memberId);

    // 나의 주차 내역 목록 조회
    List<GetParkingRes> selectParkingListByMemberId(Integer memberId);
}

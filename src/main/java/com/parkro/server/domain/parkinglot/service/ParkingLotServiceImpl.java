package com.parkro.server.domain.parkinglot.service;

import com.parkro.server.domain.parkinglot.dto.GetParkingLotRes;
import com.parkro.server.domain.parkinglot.mapper.ParkingLotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;

    // 지전별 외부 주차장 목록 조회
    @Override
    @Transactional
    public List<GetParkingLotRes> findParkingLots(Integer storeId) {
        try {
            return parkingLotMapper.selectParkingLots(storeId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

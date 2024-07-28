package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.GetParkingDetailRes;
import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import com.parkro.server.domain.parking.dto.GetParkingReq;
import com.parkro.server.domain.parking.mapper.ParkingMapper;
import com.parkro.server.domain.member.dto.GetMemberRes;
import com.parkro.server.domain.member.service.MemberService;
import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.parkro.server.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingMapper parkingMapper;
    private final MemberService memberService;

  /**
   * 주차 정보 조회
   * @param parkingId
   * @return
   */
  @Override
  public GetParkingRes findParkingByParkingId(Integer parkingId) {
    return parkingMapper.selectParkingByParkingId(parkingId);
  }

  /**
   * 결제 취소 시, 차량 상태 ENTRANCE로 업데이트
   * @param parkingId
   * @return 업데이트된 row
   */
  @Override
  public Integer modifyParkingStatus(Integer parkingId) {
    return parkingMapper.updateParkingStatus(parkingId);
  }

    // 입차
    @Override
    @Transactional
    public Integer addParking(PostParkingReq req) {

        // 입차한 차 번호로 가입된 유저 조회
        GetMemberRes member = memberService.findMemberByCarNumber(req.getCarNumber());
        if (member == null) {
            req.setMemberId(null);
        } else {
            req.setMemberId(member.getMemberId());
        }
        parkingMapper.insertParking(req);
        return req.getParkingId();
    }

    // 출차
    @Override
    @Transactional
    public Integer modifyParkingOut(PatchParkingReq req) {
        int numRowsUpdated = parkingMapper.updateParkingOut(req);
        if (numRowsUpdated == 0) {
            throw new CustomException(INVALID_PARKING_STATUS);
        }
        return numRowsUpdated;
    }
  
    // 주차 정산(전) 정보 조회
    @Override
    @Transactional(readOnly=true)
    public List<GetParkingPayRes> findParkingPay(String username) {
        GetMemberRes member = memberService.findMember(username);

        List<GetParkingPayRes> res = parkingMapper.selectParkingPay(member.getMemberId());

        if (res.isEmpty()) {
            throw new CustomException(FIND_FAIL_PARKING_INFO);
        }
        return res;
    }

    // 나의 주차 내역 목록 조회
    @Override
    @Transactional(readOnly=true)
    public List<GetParkingRes> findMyParkingList(String username, GetParkingReq req) {
        GetMemberRes member = memberService.findMember(username);
        req.setMemberId(member.getMemberId());
        List<GetParkingRes> res = parkingMapper.selectParkingListByMemberId(req);

        if (res.isEmpty()) {
            throw new CustomException(FIND_FAIL_PARKING_LIST);
        }
        return res;
    }

    // 주차 내역 삭제
    @Override
    public Integer removeParking(Integer parkingId) {
      int numRowsDeleted = parkingMapper.deleteParkingById(parkingId);
      if (numRowsDeleted == 0) {
        throw new CustomException(FAIL_DELETE_PARKING_);
      }
      return numRowsDeleted;
    }
  
    // [관리자] 주차 내역 상세 조회
    @Override
    public GetParkingDetailRes findAdminParkingDetails(Integer parkingId) {
        return parkingMapper.selectAdminParkingDetails(parkingId);
    }

    // [관리자] 지점별 주차 내역 목록 조회
    @Override
    @Transactional(readOnly=true)
    public List<GetParkingRes> findParkingListByStore(GetParkingReq req) {
      return parkingMapper.selectParkingListByStore(req);
    }
}

package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.parking.dto.GetParkingDetailRes;
import com.parkro.server.domain.parking.dto.GetParkingRes;
import com.parkro.server.domain.parking.dto.PatchParkingReq;
import com.parkro.server.domain.parking.dto.PostParkingReq;
import com.parkro.server.domain.parking.dto.GetParkingPayRes;
import com.parkro.server.domain.parking.dto.GetParkingReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParkingMapper {

  // parkingId로 주차 정보 조회
  GetParkingRes selectParkingByParkingId(Integer parkingId);

  // 결제 취소 후 parking status 업데이트
  Integer updateParkingStatus(@Param("parkingId") Integer parkingId, @Param("status") String status);
  
    // 입차
    void insertParking(PostParkingReq req);

    // 출차
    Integer updateParkingOut(PatchParkingReq req);

    // 주차 정산(전) 정보 조회
    GetParkingPayRes selectParkingPay(Integer memberId);

    // 나의 주차 내역 목록 조회
    List<GetParkingRes> selectParkingListByMemberId(GetParkingReq req);

    // 주차 내역 삭제
    Integer deleteParkingById(Integer parkingId);
  
    // [관리자] 주차 내역 상세 조회
    GetParkingDetailRes selectAdminParkingDetails(Integer parkingId);
  
    // [관리자] 지점별 주차 내역 목록 조회
    List<GetParkingRes> selectParkingListByStore(GetParkingReq req);

    // [관리자] 결제 완료
    Integer updateParkingOutById(Integer parkingId);

    // 멤버 아이디 업데이트
    void updateMemberId(PostMemberReq postMemberIdReq);
    // 멤버 아이디 삭제
    void deleteMemberId(String carNumber);
}

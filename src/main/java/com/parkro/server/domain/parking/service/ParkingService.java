package com.parkro.server.domain.parking.service;

import com.parkro.server.domain.parking.dto.*;

import java.util.List;

/**
 * 주차
 *
 * @author 김민정
 * @since 2024.07.29
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  김민정      최초 생성
 * 2024.07.26  김지수      parking_id로 주차 정보 조회
 * 2024.07.27  김민정      입차 시, 주차 정보 등록
 * 2024.07.28  김민정      출차 시, 잔여 좌석 수 업데이트
 * 2024.07.28  김민정      주차 정산(전) 정보 조회
 * 2024.07.28  김민정      나의 주차 내역 목록 조회
 * 2024.07.28  김민정      주차 내역 삭제
 * 2024.07.28  김민정      [관리자] 주차 내역 상세 조회
 * 2024.07.28  김민정      [관리자] 지점별 주차 내역 목록 조회
 * 2024.07.28  김민정      [관리자] 결제 완료
 * 2024.08.03  김지수      결제 취소 시, 차량 상태 ENTRANCE로 업데이트
 * 2024.08.03  김지수      결제 취소 시, 차량 상태 PAY로 업데이트
 * </pre>
 */
public interface ParkingService {

  // parking_id로 주차 정보 조회
  GetParkingRes findParkingByParkingId(Integer parkingId);

  // 입차 시, 주차 정보 등록
  Integer addParking(PostParkingReq req);

  // 출차 시, 잔여 좌석 수 업데이트
  Integer modifyParkingOut(PatchParkingReq req);

  // 주차 정산(전) 정보 조회
  GetParkingPayRes findParkingPay(String username);

  // 나의 주차 내역 목록 조회
  List<GetParkingRes> findMyParkingList(String username, GetParkingReq req);

  // 주차 내역 삭제
  Integer removeParking(Integer parkingId);

  // [관리자] 주차 내역 상세 조회
  GetParkingDetailRes findAdminParkingDetails(Integer parkingId);

  // [관리자] 지점별 주차 내역 목록 조회
  List<GetParkingRes> findParkingListByStore(GetParkingReq req);

  // [관리자] 결제 완료
  Integer modifyParkingOutById(Integer parkingId);

  // 결제 취소 시, 차량 상태 ENTRANCE로 업데이트
  Integer modifyParkingStatusEnter(Integer parkingId);

  // 결제 취소 시, 차량 상태 PAY로 업데이트
  Integer modifyParkingStatusPay(Integer parkingId);
}

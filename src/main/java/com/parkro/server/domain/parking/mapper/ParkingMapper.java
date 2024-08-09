package com.parkro.server.domain.parking.mapper;

import com.parkro.server.domain.member.dto.PostMemberReq;
import com.parkro.server.domain.parking.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author 김민정
 * @since 2024.07.25
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.25   김민정      최초 생성
 * 2024.07.29   양재혁      멤버 아이디 업데이트
 * 2024.07.29   양재혁      멤버 아이디 삭제
 * </pre>
 */

/**
 * 주차
 *
 * @author 김민정
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  김민정      최초 생성
 * 2024.07.26  김지수      parkingId로 주차 정보 조회
 * 2024.07.27  김민정      주차 정보 삽입
 * 2024.07.27  김민정      주차 상태 EXIT로 업데이트
 * 2024.07.28  김민정      주차 정산(전) 정보 조회
 * 2024.07.28  김민정      memberId로 주차 정보 조회
 * 2024.07.28  김민정      주차 deletedDate 현재 시간으로 변경
 * 2024.07.28  김민정      [관리자] 주차 내역 상세 조회
 * 2024.07.28  김민정      [관리자] 지점별 주차 내역 목록 조회
 * 2024.07.28  김민정      [관리자] 결제 완료
 * 2024.07.29  양재혁      멤버 아이디 업데이트
 * 2024.07.29  양재혁      멤버 아이디 삭제
 * 2024.08.03  김지수      결제 취소 후 parking status 업데이트
 * </pre>
 */
@Mapper
public interface ParkingMapper {

  // parkingId로 주차 정보 조회
  GetParkingRes selectParkingByParkingId(Integer parkingId);

  // 주차 정보 삽입
  void insertParking(PostParkingReq req);

  // 주차 상태 EXIT로 업데이트
  Integer updateParkingOut(PatchParkingReq req);

  // 주차 정산(전) 정보 조회
  GetParkingPayRes selectParkingPay(Integer memberId);

  // memberId로 주차 정보 조회
  List<GetParkingRes> selectParkingListByMemberId(GetParkingReq req);

  // 주차 deletedDate 현재 시간으로 변경
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

  // 결제 취소 후 parking status 업데이트
  Integer updateParkingStatus(@Param("parkingId") Integer parkingId, @Param("status") String status);
}

package com.parkro.server.domain.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주차 상태 변경 DTO
 *
 * @author 김민정
 * @since 2024.07.27
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.27   김민정       최초 생성
 * </pre>
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatchParkingReq {

  private Integer parkingLotId;
  private String carNumber;
}

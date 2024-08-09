package com.parkro.server.domain.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주차장 DTO
 *
 * @author 김민정
 * @since 2024.07.26
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.26   김민정       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingLotRes {

  private String name;
  private String address;
  private Double latitude;
  private Double longitude;
  private String isInternal;
  private Long totalSpaces;
  private Long usedSpaces;
}

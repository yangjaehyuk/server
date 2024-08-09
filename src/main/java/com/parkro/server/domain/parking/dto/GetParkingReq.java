package com.parkro.server.domain.parking.dto;

import lombok.*;

/**
 * 주차 내역 조회 요청 DTO
 *
 * @author 김민정
 * @since 2024.07.28
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.28   김민정      최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingReq {

  @Setter
  private Integer memberId;
  private Integer storeId;
  private Integer parkingLotId;
  private String date;
  private String carNumber;
  private Integer page;

  @Builder.Default
  private Integer pageSize = 10;
}

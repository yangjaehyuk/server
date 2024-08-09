package com.parkro.server.domain.parking.dto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.*;
import java.util.Date;

/**
 * 주차 내역 조회 응답 DTO
 *
 * @author 김지수
 * @since 2024.07.28
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.28   김지수      최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetParkingRes {
  @Setter
  private Integer parkingId;
  private Integer parkingLotId;
  private Integer memberId;
  private String storeName;
  private String parkingLotName;
  private String carNumber;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date entranceDate;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date exitDate;
  private String status;
}
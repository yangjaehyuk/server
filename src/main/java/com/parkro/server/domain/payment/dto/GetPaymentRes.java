package com.parkro.server.domain.payment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 결제 목록 조회 응답 DTO
 *
 * @author 김지수
 * @since 2024.07.28
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  김지수      최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetPaymentRes {

  private Integer paymentId;
  private Integer parkingId;
  private Integer totalParkingTime;
  private Integer totalPrice;
  private Integer parkingTimeHour;
  private Integer parkingTimeMinute;
  private Integer receiptDiscountTime;
  private Integer couponDiscountTime;
  private String card;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date paymentTime;
  private String carNumber;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date entranceDate;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date exitDate;
  private String parkingStatus;
  private Integer parkingLotId;
  private Integer storeId;
  private String parkingLotName;
  private String storeName;
}

package com.parkro.server.domain.payment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetPaymentCouponRes {

  private Integer memberCouponId;
  private Integer couponId;
  private Integer parkingLotId;
  private String status;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date createdDate;
  private Integer discountHour;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date endDate; // 쿠폰 종료 기간
}

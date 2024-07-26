package com.parkro.server.domain.payment.dto;

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
  private Integer memberId;
  private Integer couponId;
  private Integer parkingLotId;
  private String status;
  private Date createdDate;
  private Integer discountHour;
  private Date endDate; // 쿠폰 종료 기간
}

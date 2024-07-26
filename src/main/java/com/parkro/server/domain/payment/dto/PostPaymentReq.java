package com.parkro.server.domain.payment.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class PostPaymentReq {

  @Setter
  private Integer paymentId;
  private String username;
  private Integer parkingId;
  private Integer memberCouponId;
  private Integer receiptId;
  private Integer discountTime;
  private Integer totalParkingTime;
  private Integer totalPrice;
  private String card;
  private Date paymentTime;
  private String paymentKey;
  private Date cancelledDate;
  private String status;
}

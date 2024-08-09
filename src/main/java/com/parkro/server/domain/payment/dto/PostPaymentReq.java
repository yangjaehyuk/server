package com.parkro.server.domain.payment.dto;

import lombok.*;

import java.util.Date;

/**
 * 결제 정보 등록 요청 DTO
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
public class PostPaymentReq {

  @Setter
  private Integer paymentId;
  private String username;
  private Integer parkingId;
  private Integer memberCouponId;
  private Integer receiptId;
  private Integer couponDiscountTime;
  private Integer receiptDiscountTime;
  private Integer totalParkingTime;
  private Integer totalPrice;
  private String card;
  private Date paymentTime;
  private String paymentKey;
  private Date cancelledDate;
  private String status;
}

package com.parkro.server.domain.payment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 정산 시, 사용자가 보유한 쿠폰 목록 DTO
 *
 * @author 김지수
 * @since 2024.07.26
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  김지수      최초 생성
 * </pre>
 */
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

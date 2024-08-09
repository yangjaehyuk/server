package com.parkro.server.domain.receipt.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.*;

import java.util.Date;

/**
 * 영수증 정보 조회 DTO
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
public class GetReceiptRes {

  private Integer receiptId;
  private Integer storeId;
  private Integer totalPrice;
  private String status;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date createdDate;
}

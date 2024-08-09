package com.parkro.server.domain.receipt.dto;

import lombok.*;

/**
 * 영수증 등록 DTO
 *
 * @author 김지수
 * @since 2024.07.25
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.25   김지수      최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostReceiptReq {

  private Integer receiptId;
  private Integer storeId;
  private Integer totalPrice;
}

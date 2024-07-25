package com.parkro.server.domain.receipt.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostReceiptReq {

  private Integer receiptId;
  private Integer storeId;
  private Integer totalPrice;
}

package com.parkro.server.domain.receipt.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetReceiptRes {

  private Integer receiptId;
  private Integer storeId;
  private Integer totalPrice;
  private String status;
  private Date createdDate;
}

package com.parkro.server.domain.receipt.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
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

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date createdDate;
}

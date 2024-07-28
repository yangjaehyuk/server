package com.parkro.server.domain.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingReq {
  private String storeId;
  private String date;
  private String carNumber;
  private Integer page;
  private Integer pageSize;
}

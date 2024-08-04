package com.parkro.server.domain.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingReq {

  @Setter
  private Integer memberId;
  private Integer storeId;
  private String date;
  private String carNumber;
  private Integer page;

  @Builder.Default
  private Integer pageSize = 10;
}

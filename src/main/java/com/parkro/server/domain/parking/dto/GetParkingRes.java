package com.parkro.server.domain.parking.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetParkingRes {

  @Setter
  private Integer parkingId;
  private Integer parkingLotId;
  private Integer memberId;
  private String storeName;
  private String parkingLotName;
  private String carNumber;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date entranceDate;

  @JsonSerialize(using = KSTDateSerializer.class)
  private Date exitDate;

  private String status;
}

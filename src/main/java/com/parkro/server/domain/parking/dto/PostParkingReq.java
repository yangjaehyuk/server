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
public class PostParkingReq {

    @Setter
    private Integer parkingId;
    private Integer parkingLotId;
    @Setter
    private Integer memberId;
    private String carNumber;
}

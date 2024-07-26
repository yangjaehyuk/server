package com.parkro.server.domain.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingLotRes {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String isInternal;
    private Long totalSpaces;
    private Long usedSpaces;
}

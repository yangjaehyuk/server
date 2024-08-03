package com.parkro.server.domain.parking.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetParkingPayRes {

    private String carNumber;
    private Integer parkingId;
    private String status;
    private String storeName;
    private String parkingLotName;
    @JsonSerialize(using = KSTDateSerializer.class)
    private Date entranceDate;
    private Integer parkingTimeHour;
    private Integer parkingTimeMinute;
    private Integer perPrice;
}

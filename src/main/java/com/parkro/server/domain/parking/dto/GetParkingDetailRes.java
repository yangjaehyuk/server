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
public class GetParkingDetailRes {

    private String carNumber;
    private String storeName;
    private String parkingLotName;
    @JsonSerialize(using = KSTDateSerializer.class)
    private Date entranceDate;
    @JsonSerialize(using = KSTDateSerializer.class)
    private Date exitDate;
    private String parkingStatus;

    @JsonSerialize(using = KSTDateSerializer.class)
    private Date paymentDate;
    private Integer couponDiscountTime;
    private Integer receiptDiscountTime;
    private Integer totalParkingTime;
    private Integer parkingTimeHour;
    private Integer parkingTimeMinute;
    private Integer totalPrice;
}

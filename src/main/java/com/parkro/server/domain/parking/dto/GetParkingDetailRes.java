package com.parkro.server.domain.parking.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parkro.server.util.KSTDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 주차 정보 상세 DTO
 *
 * @author 김민정
 * @since 2024.07.28
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.28   김민정      최초 생성
 * </pre>
 */
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

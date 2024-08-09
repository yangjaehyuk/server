package com.parkro.server.domain.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주차 등록 DTO
 *
 * @author 김민정
 * @since 2024.07.27
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.27   김민정       최초 생성
 * </pre>
 */
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

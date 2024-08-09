package com.parkro.server.domain.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 입차 알림 DTO
 *
 * @author 김민정
 * @since 2024.07.29
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.29   김민정       최초 생성
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingEntryDTO {

  private String fcmToken;
  private String carNumber;
}

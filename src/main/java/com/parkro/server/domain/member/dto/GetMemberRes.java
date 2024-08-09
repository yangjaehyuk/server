package com.parkro.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 멤버 정보
 *
 * @author 김지수
 * @since 2024.07.26
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.26   김지수      최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetMemberRes {

  private Integer memberId;
  private String username;
  private String password;
  private String nickname;
  private String phoneNumber;
  private String ROLE;
  private String carNumber;
  private Integer profileCar;
  private String status;
  private Date createdDate;
  private Date deletedDate;
  private String fcmToken;
}

package com.parkro.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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

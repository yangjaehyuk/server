package com.parkro.server.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * 회원 가입 클래스
 *
 * @author 양재혁
 * @since 2024.07.25
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  양재혁       최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberReq implements UserDetails {

  private Integer memberId;
  @NotBlank(message = "아이디는 필수 입력 값입니다.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$",
          message = "영문 숫자 포함 6 ~ 12자이어야 합니다.")
  private String username;

  @NotBlank(message = "닉네임은 필수 입력 값입니다.")
  private String nickname;

  @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
  @Pattern(regexp = "^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$",
          message = "-를 붙여야 합니다.")
  private String phoneNumber;

  private String carNumber;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?& ]{8,12}$",
          message = "영문 대소문자, 숫자, 특수문자 포함 8 ~ 12자이어야 합니다.")

  @Setter
  private String password;

  private String role;

  private String fcmToken;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Collection<? extends GrantedAuthority> authorities;

  public String getCarNumber() {
    return carNumber != null && !carNumber.trim().isEmpty() ? carNumber : null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public String getPassword() {
    return this.password;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public String getUsername() {
    return this.username;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isEnabled() {
    return true;
  }
}

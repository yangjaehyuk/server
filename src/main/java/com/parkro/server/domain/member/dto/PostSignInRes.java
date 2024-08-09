package com.parkro.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * 회원 수정 성공 시 전달되는 응답에 담을 데이터 클래스
 *
 * @author 양재혁
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.02  양재혁       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostSignInRes {
    private String username;
    private Integer carProfile;
}

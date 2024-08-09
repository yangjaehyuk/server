package com.parkro.server.domain.member.dto;

import lombok.*;
/**
 * 로그인 성공 시 전달되는 응답에 담을 데이터 클래스
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostMemberRes {
    private String token;
    private PostSignInRes postSignInRes;
}

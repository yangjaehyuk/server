package com.parkro.server.domain.member.dto;

import lombok.*;
/**
 * 로그인 성공 시 전달되는 응답에 담을 데이터 클래스
 *
 * @author 양재혁
 *
 * <pre>
 * 수정자       수정내용
 * ----------  --------
 * 양재혁       최초 생성
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

package com.parkro.server.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * 에러 DTO
 *
 * @author 김민정
 * @since 2024.07.25
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  김민정      최초 생성
 *
 * </pre>
 */
@Getter
@Builder
public class ErrorResponse {
  private final int status;
  private final String errorCode;
  private final String message;
}

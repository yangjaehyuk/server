package com.parkro.server.aop;

import com.parkro.server.exception.CustomException;
import com.parkro.server.exception.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리
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
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    log.error(ex.getMessage(), ex);
    ErrorResponse errorResponse = ErrorResponse.builder()
            .status(ex.getErrorCode().getStatus())
            .errorCode(ex.getErrorCode().name())
            .message(ex.getErrorCode().getMessage())
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    log.error(ex.getMessage(), ex);
    ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorCode("INTERNAL_SERVER_ERROR")
            .message(ex.getMessage())
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

package com.parkro.server.exception;

import lombok.Getter;

/**
 * 커스텀 익셉션
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
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

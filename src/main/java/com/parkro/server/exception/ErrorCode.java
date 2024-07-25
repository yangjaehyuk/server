package com.parkro.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 */
    FIND_FAIL_USER_ID(400, "존재하지 않는 유저입니다."),

    /* 500 */
    DATABASE_ERROR(500, "데이터베이스 오류가 발생했습니다."),
    ;

    private final int status;
    private final String message;
}

package com.parkro.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 */
    FIND_FAIL_USER_ID(400, "존재하지 않는 유저입니다."),

    /* 404 */
    RECEIPT_NOT_FOUND(404, "영수증 정보를 찾을 수 없습니다."),

    /* 409 */
    FIND_DUPLICATED_USERNAME(409, "중복된 유저 아이디 입니다."),

    /* 500 */
    DATABASE_ERROR(500, "데이터베이스 오류가 발생했습니다."),
    ;

    private final int status;
    private final String message;
}

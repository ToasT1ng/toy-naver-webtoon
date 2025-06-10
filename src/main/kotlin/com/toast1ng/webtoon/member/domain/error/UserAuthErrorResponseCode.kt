package com.toast1ng.webtoon.member.domain.error

import com.toast1ng.webtoon.common.response.ResponseCode
import org.springframework.http.HttpStatus

enum class UserAuthErrorResponseCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus
) : ResponseCode {
    USER_NOT_FOUND(40401, "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(40101, "잘못된 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS(40001, "이미 존재하는 사용자입니다.", HttpStatus.BAD_REQUEST),
    ;
}
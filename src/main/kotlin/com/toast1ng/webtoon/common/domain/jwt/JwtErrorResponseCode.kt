package com.toast1ng.webtoon.common.domain.jwt

import com.toast1ng.webtoon.common.response.ResponseCode
import org.springframework.http.HttpStatus

enum class JwtErrorResponseCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus
) : ResponseCode {
    JWT_TOKEN_EXPIRED(40191, "JWT 토큰이 만료되었습니다. 새로 로그인해주세요.", HttpStatus.UNAUTHORIZED),
    JWT_TOKEN_INVALID(40192, "유효하지 않은 JWT 토큰입니다.", HttpStatus.UNAUTHORIZED),
    JWT_TOKEN_MALFORMED(40193, "잘못된 형식의 JWT 토큰입니다.", HttpStatus.UNAUTHORIZED),
    JWT_TOKEN_SIGNATURE_INVALID(40194, "JWT 토큰의 서명이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),
    JWT_TOKEN_UNSUPPORTED(40195, "지원되지 않는 JWT 토큰입니다.", HttpStatus.UNAUTHORIZED),
}
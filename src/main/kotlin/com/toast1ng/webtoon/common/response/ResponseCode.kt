package com.toast1ng.webtoon.common.response

import org.springframework.http.HttpStatus

interface ResponseCode {
    val code: Int
    val message: String
    val httpStatus: HttpStatus
}

enum class CommonSuccessResponseCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus
) : ResponseCode {
    OK(20000, "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    CREATED(20100, "리소스가 성공적으로 생성되었습니다.", HttpStatus.CREATED),
    NO_CONTENT(20400, "요청이 성공적으로 처리되었으나 반환할 데이터가 없습니다.", HttpStatus.NO_CONTENT),;
}

enum class CommonErrorResponseCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus
) : ResponseCode{
    BAD_REQUEST(40000, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(40100, "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(40300, "접근이 금지된 리소스입니다.", HttpStatus.FORBIDDEN),
    NOT_FOUND(40400, "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(50000, "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(50300, "서비스를 사용할 수 없습니다. 잠시 후 다시 시도해주세요.", HttpStatus.SERVICE_UNAVAILABLE),;
}
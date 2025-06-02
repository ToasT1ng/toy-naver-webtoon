package com.toast1ng.webtoon.common.response

import org.springframework.http.ResponseEntity

object ResponseEntityFactory {
    fun <T> success(
        data: T? = null,
        code: Int = CommonSuccessResponseCode.OK.code,
        message: String = CommonSuccessResponseCode.OK.message
    ): ResponseEntity<SuccessResponse<T>> {
        return ResponseEntity
            .ok(SuccessResponse(code = code, message = message, data = data))
    }

    fun error(
        code: Int,
        message: String
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .internalServerError()
            .body(ErrorResponse(code = code, message = message))
    }

    fun error(
        responseCode: ResponseCode
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(responseCode.httpStatus)
            .body(
                ErrorResponse(
                    code = responseCode.code,
                    message = responseCode.message
                )
            )
    }
}
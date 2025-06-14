package com.toast1ng.webtoon.common

import com.toast1ng.webtoon.common.response.CommonErrorResponseCode
import com.toast1ng.webtoon.common.response.ErrorResponse
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.utils.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = getLogger()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.error("Validation exception occurred", ex)
        return ResponseEntityFactory.error(CommonErrorResponseCode.BAD_REQUEST)
    }

    @ExceptionHandler(WebtoonException::class)
    fun handleWebtoonException(ex: WebtoonException): ResponseEntity<ErrorResponse> {
        log.error("Webtoon exception occurred", ex)
        return ResponseEntityFactory.error(ex.code)
    }


    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error("Unhandled exception occurred", ex)
        return ResponseEntityFactory.error(CommonErrorResponseCode.INTERNAL_SERVER_ERROR)
    }
}
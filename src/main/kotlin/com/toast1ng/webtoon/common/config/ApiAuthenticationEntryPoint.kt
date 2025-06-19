package com.toast1ng.webtoon.common.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.toast1ng.webtoon.common.domain.auth.JwtTokenAuthenticationException
import com.toast1ng.webtoon.common.response.CommonErrorResponseCode
import com.toast1ng.webtoon.common.response.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class ApiAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val errorResponseCode = when (authException) {
            is JwtTokenAuthenticationException -> authException.code
            else -> CommonErrorResponseCode.UNAUTHORIZED
        }

        response.status = errorResponseCode.code
        response.contentType = "application/json;charset=UTF-8"
        response.writer.write(
            jacksonObjectMapper().writeValueAsString(
                ErrorResponse(
                    code = errorResponseCode.code,
                    message = errorResponseCode.message
                )
            )
        )
    }
}
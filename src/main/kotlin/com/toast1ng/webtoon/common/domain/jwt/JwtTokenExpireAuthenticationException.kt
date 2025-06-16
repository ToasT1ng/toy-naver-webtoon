package com.toast1ng.webtoon.common.domain.jwt

import org.springframework.security.core.AuthenticationException

class JwtTokenExpireAuthenticationException(
    val code: JwtErrorResponseCode,
) : AuthenticationException(code.message)
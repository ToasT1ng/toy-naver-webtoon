package com.toast1ng.webtoon.common.domain.auth

import org.springframework.security.core.AuthenticationException

class JwtTokenAuthenticationException(
    val code: JwtErrorResponseCode,
) : AuthenticationException(code.message)
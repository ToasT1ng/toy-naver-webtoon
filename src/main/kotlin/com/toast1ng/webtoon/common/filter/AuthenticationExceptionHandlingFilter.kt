package com.toast1ng.webtoon.common.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class AuthenticationExceptionHandlingFilter(
    private val authenticationEntryPoint: AuthenticationEntryPoint
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            chain.doFilter(request, response)
        } catch (ex: AuthenticationException) {
            if (!response.isCommitted) {
                authenticationEntryPoint.commence(request as HttpServletRequest, response as HttpServletResponse, ex)
            }
        }
    }
}
package com.toast1ng.webtoon.common.filter

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class RequestLoggingFilter : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(RequestLoggingFilter::class.java)

    //TODO 로그 injection 등 보안 이슈가 발생할 수 있는 포인트 확인
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logHeaders(request)
        filterChain.doFilter(request, response)
    }

    private fun logHeaders(request: HttpServletRequest) {
        val headers = request.headerNames.toList().associateWith { request.getHeader(it) }
        val headerLog = headers.entries.joinToString(", ") { "${it.key}=${it.value}" }
        log.info("Incoming Request: [${request.method}] ${request.requestURI} | Headers: $headerLog")
    }

    private fun java.util.Enumeration<String>.toList(): List<String> {
        val list = mutableListOf<String>()
        while (this.hasMoreElements()) {
            list.add(this.nextElement())
        }
        return list
    }
}
package com.toast1ng.webtoon.common.filter

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.common.domain.auth.JwtErrorResponseCode
import com.toast1ng.webtoon.common.domain.auth.JwtTokenAuthenticationException
import com.toast1ng.webtoon.common.utils.getLogger
import com.toast1ng.webtoon.member.application.port.`in`.QueryTokenUseCase
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService,
    private val queryTokenUseCase: QueryTokenUseCase,
) : OncePerRequestFilter() {
    private val log = getLogger()

    private val excludeUriPatterns = listOf("/auth/**")

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.servletPath
        return excludeUriPatterns.any { antPattern ->
            AntPathMatcher().match(antPattern, path)
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        try {
            if (authHeader?.startsWith("Bearer ") == true) {
                val accessToken = authHeader.removePrefix("Bearer ")
                queryTokenUseCase.validateTokenIsNotBlackListed(accessToken)
                val username = jwtProvider.getUserId(accessToken)
                require(username != null) { "User ID not found in JWT token" }
                val userDetails = userDetailsService.loadUserByUsername(username.toString())
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
            }
        } catch (e: JwtException) {
            log.error("JwtAuthenticationFilter ERROR: ${e.message}", e)
            val responseCode = when (e) {
                is ExpiredJwtException -> JwtErrorResponseCode.JWT_TOKEN_EXPIRED
                is MalformedJwtException -> JwtErrorResponseCode.JWT_TOKEN_MALFORMED
                is UnsupportedJwtException -> JwtErrorResponseCode.JWT_TOKEN_UNSUPPORTED
                is SignatureException -> JwtErrorResponseCode.JWT_TOKEN_SIGNATURE_INVALID
                else -> JwtErrorResponseCode.JWT_TOKEN_INVALID
            }
            throw JwtTokenAuthenticationException(responseCode)

        } catch (ex: Exception) {
            log.error("JwtAuthenticationFilter ERROR " + ex.message, ex)
            throw JwtTokenAuthenticationException(JwtErrorResponseCode.INVALID_TOKEN)
        }

        filterChain.doFilter(request, response)
    }
}
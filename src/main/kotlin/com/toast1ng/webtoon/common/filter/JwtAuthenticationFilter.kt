package com.toast1ng.webtoon.common.filter

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.common.utils.getLogger
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {
    private val log = getLogger()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")

        try {
            if (authHeader?.startsWith("Bearer ") == true) {
                val token = authHeader.substring(7)
                val username = jwtProvider.getUsername(token)

                if (username != null && SecurityContextHolder.getContext().authentication == null) {
                    val userDetails = userDetailsService.loadUserByUsername(username)

                    val authentication = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )

                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        } catch (ex: Exception) {
            log.error("JwtAuthenticationFilter ERROR " + ex.message, ex)
        }

        filterChain.doFilter(request, response)
    }
}
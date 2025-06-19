package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.member.application.port.`in`.MakeTokenUseCase
import com.toast1ng.webtoon.member.application.port.`in`.RefreshTokenUseCase
import com.toast1ng.webtoon.member.domain.Tokens
import com.toast1ng.webtoon.member.domain.User
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: CustomUserDetailsService,
) : MakeTokenUseCase, RefreshTokenUseCase {
    override fun makeTokens(
        id: Long,
        isAutoLogin: Boolean
    ): Tokens {
        return Tokens(
            accessToken = jwtProvider.createAccessToken(id),
            refreshToken = jwtProvider.createRefreshToken(id, isAutoLogin)
        )
    }

    override fun refreshAccessToken(refreshToken: String): String {
        check(jwtProvider.isValid(refreshToken)) {
            "Invalid refresh token"
        }

        val userId = jwtProvider.getUserId(refreshToken)
            ?: throw Exception("UserID not found in refresh token")

        val userDetails = userDetailsService.loadUserByUsername(userId.toString())
        check(userDetails is User) { "Not a User instance" }
        return jwtProvider.createAccessToken(userDetails.id)
    }
}
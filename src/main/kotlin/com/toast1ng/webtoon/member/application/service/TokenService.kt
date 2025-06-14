package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.member.application.port.`in`.TokenUseCase
import com.toast1ng.webtoon.member.domain.Tokens
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val jwtProvider: JwtProvider,
) : TokenUseCase {
    override fun makeTokens(
        username: String,
        isAutoLogin: Boolean
    ): Tokens {
        return Tokens(
            accessToken = jwtProvider.createAccessToken(username),
            refreshToken = jwtProvider.createRefreshToken(username, isAutoLogin)
        )
    }

    override fun makeAccessToken(username: String): String {
        return jwtProvider.createAccessToken(username)
    }
}
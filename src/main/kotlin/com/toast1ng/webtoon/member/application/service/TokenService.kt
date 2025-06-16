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
        id: Long,
        isAutoLogin: Boolean
    ): Tokens {
        return Tokens(
            accessToken = jwtProvider.createAccessToken(id),
            refreshToken = jwtProvider.createRefreshToken(id, isAutoLogin)
        )
    }

    override fun makeAccessToken(id: Long): String {
        return jwtProvider.createAccessToken(id)
    }
}
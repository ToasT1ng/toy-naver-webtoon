package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.LogoutUserUseCase
import org.springframework.stereotype.Service

@Service
class LogoutService(
    private val redisJwtTokenService: RedisJwtTokenService,
) : LogoutUserUseCase {
    override fun logout(accessToken: String) {
        redisJwtTokenService.storeBlacklistToken(accessToken)
    }
}
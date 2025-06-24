package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.member.application.port.`in`.StoreTokenUseCase
import com.toast1ng.webtoon.member.application.port.`in`.QueryTokenUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.StoreWhitelistTokenCommand
import com.toast1ng.webtoon.member.application.port.out.ReadBlacklistTokenPort
import com.toast1ng.webtoon.member.application.port.out.ReadWhitelistTokenPort
import com.toast1ng.webtoon.member.application.port.out.StoreBlacklistTokenPort
import com.toast1ng.webtoon.member.application.port.out.StoreWhitelistTokenPort
import com.toast1ng.webtoon.member.application.port.out.cache.BlacklistCache
import com.toast1ng.webtoon.member.application.port.out.cache.WhitelistCache
import org.springframework.stereotype.Service

@Service
class RedisJwtTokenService(
    private val storeWhitelistTokenPort: StoreWhitelistTokenPort,
    private val storeBlacklistTokenPort: StoreBlacklistTokenPort,
    private val readWhitelistTokenPort: ReadWhitelistTokenPort,
    private val readBlacklistTokenPort: ReadBlacklistTokenPort,
    private val jwtProvider: JwtProvider,
) : StoreTokenUseCase, QueryTokenUseCase {

    override fun storeWhitelistToken(command: StoreWhitelistTokenCommand) {
        val remainingSeconds = jwtProvider.getRemainingSeconds(command.refreshToken)
            ?: throw IllegalArgumentException("Invalid refresh token")
        storeWhitelistTokenPort.add(
            WhitelistCache(
                token = command.refreshToken,
                userId = command.userId.toString(),
                ip = command.ip ?: "",
                device = command.device ?: "",
                ttlSeconds = remainingSeconds
            )
        )
    }

    override fun storeBlacklistToken(accessToken: String) {
        val remainingSeconds = jwtProvider.getRemainingSeconds(accessToken)
            ?: throw IllegalArgumentException("Invalid access token")
        storeBlacklistTokenPort.add(
            BlacklistCache(
                accessToken = accessToken,
                ttlSeconds = remainingSeconds
            )
        )
    }

    override fun validateTokenIsWhiteListed(refreshToken: String) {
        val userId = jwtProvider.getUserId(refreshToken)
            ?: throw IllegalArgumentException("Invalid refresh token")
        check(readWhitelistTokenPort.isWhitelistedToken(refreshToken, userId))
    }

    override fun validateTokenIsNotBlackListed(accessToken: String) {
        check(!readBlacklistTokenPort.isBlacklistedToken(accessToken))
    }
}
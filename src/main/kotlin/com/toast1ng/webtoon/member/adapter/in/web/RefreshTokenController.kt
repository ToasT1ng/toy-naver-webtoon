package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.response.RefreshedTokenResponse
import com.toast1ng.webtoon.member.application.port.`in`.RefreshTokenUseCase
import com.toast1ng.webtoon.member.application.service.RedisJwtTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshTokenController(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val redisJwtTokenService: RedisJwtTokenService,
) {
    @PostMapping("/auth/refresh")
    fun refreshAccessToken(
        @RequestHeader("Authorization") refreshTokenHeader: String? = null
    ): ResponseEntity<SuccessResponse<RefreshedTokenResponse>> {
        val refreshToken = requireNotNull(refreshTokenHeader?.removePrefix("Bearer ")) {
            "Refresh token is missing"
        }
        redisJwtTokenService.validateTokenIsWhiteListed(refreshToken)
        val accessToken = refreshTokenUseCase.refreshAccessToken(refreshToken)
        //TODO: redis에 저장된 refresh token을 delete하는 로직 추가?
        return ResponseEntityFactory.success(
            RefreshedTokenResponse(
                accessToken = accessToken
            )
        )
    }
}
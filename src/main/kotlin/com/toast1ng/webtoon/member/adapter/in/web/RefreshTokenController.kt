package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.response.RefreshedTokenResponse
import com.toast1ng.webtoon.member.application.port.`in`.RefreshTokenUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshTokenController(
    private val refreshTokenUseCase: RefreshTokenUseCase,
) {
    //TODO 테스트 코드 작성 및 검증
    @PostMapping("/auth/refresh")
    fun refreshAccessToken(
        @RequestHeader("Authorization") refreshTokenHeader: String? = null
    ): ResponseEntity<SuccessResponse<RefreshedTokenResponse>> {
        //TODO: refreshToken이 redis에 있는지 확인하는 로직 추가 필요
        val refreshToken = requireNotNull(refreshTokenHeader?.removePrefix("Bearer ")) {
            "Refresh token is missing"
        }
        val accessToken = refreshTokenUseCase.refreshAccessToken(refreshToken)

        return ResponseEntityFactory.success(
            RefreshedTokenResponse(
                accessToken = accessToken
            )
        )
    }
}
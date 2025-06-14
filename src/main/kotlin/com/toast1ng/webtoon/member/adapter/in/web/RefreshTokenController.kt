package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.response.RefreshedTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshTokenController(
    private val jwtProvider: JwtProvider
) {
    //TODO 테스트 코드 작성 및 검증
    @PostMapping("/auth/refresh")
    fun refreshAccessToken(
        @RequestHeader("Authorization") refreshTokenHeader: String
    ): ResponseEntity<SuccessResponse<RefreshedTokenResponse>> {
        val refreshToken = refreshTokenHeader.removePrefix("Bearer ")

        if (!jwtProvider.isValid(refreshToken)) {
            throw Exception("Invalid refresh token")
        }

        val username = jwtProvider.getUsername(refreshToken)

        if (username == null){
            throw Exception("Username not found in refresh token")
        }

        //TODO redis에서 refresh token 유효성 확인

        val newAccessToken = jwtProvider.createAccessToken(username)
        return ResponseEntityFactory.success(
            RefreshedTokenResponse(
                accessToken = newAccessToken
            )
        )
    }
}
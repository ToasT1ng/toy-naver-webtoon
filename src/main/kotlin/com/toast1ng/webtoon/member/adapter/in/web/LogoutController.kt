package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.application.port.`in`.LogoutUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutController(
    private val logoutUserUseCase: LogoutUserUseCase,
) {
    //TODO 테스트코드 작성 및 검증
    @PostMapping("/auth/logout")
    fun logout(
        @RequestHeader("Authorization") accessTokenHeader: String? = null
    ): ResponseEntity<SuccessResponse<Any?>> {
        val accessToken = requireNotNull(accessTokenHeader?.removePrefix("Bearer ")) {
            "Access token is missing"
        }
        logoutUserUseCase.logout(accessToken)
        return ResponseEntityFactory.success()
    }
}
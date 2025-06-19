package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.request.LoginRequest
import com.toast1ng.webtoon.member.adapter.`in`.web.response.LoginResponse
import com.toast1ng.webtoon.member.application.port.`in`.LoginUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.MakeTokenUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginUseCase: LoginUserUseCase,
    private val makeTokenUseCase: MakeTokenUseCase,
) {
    @PostMapping("/auth/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<SuccessResponse<LoginResponse>> {
        val user = loginUseCase.login(
            LoginCommand(
                username = request.username,
                password = request.password
            )
        )
        val tokens = makeTokenUseCase.makeTokens(id = user.id, isAutoLogin = false) //TODO isAutoLogin 처리 필요
        //TODO: refresh 토큰을 Redis에 저장하는 로직 추가 필요
        return ResponseEntityFactory.success(
            LoginResponse(
                accessToken = tokens.accessToken,
                refreshToken = tokens.refreshToken,
                userId = user.id,
                username = user.username,
                nickname = user.nickname,
                profileImageUrl = user.profileImage
            )
        )
    }
}

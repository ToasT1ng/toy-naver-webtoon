package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.request.LoginRequest
import com.toast1ng.webtoon.member.application.port.`in`.LoginUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginUseCase: LoginUserUseCase
) {
    @PostMapping("/auth/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<SuccessResponse<Any?>> {
        loginUseCase.login(
            LoginCommand(
                username = request.username,
                password = request.password
            )
        )
        return ResponseEntityFactory.success()
    }
}

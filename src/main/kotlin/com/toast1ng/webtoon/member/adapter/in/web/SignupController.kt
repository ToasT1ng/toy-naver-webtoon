package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.request.SignupRequest
import com.toast1ng.webtoon.member.application.port.`in`.SignupUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.SignupCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignupController(
    private val signupUseCase: SignupUserUseCase,
) {
    @PostMapping("/auth/signup")
    fun signup(@Valid @RequestBody request: SignupRequest): ResponseEntity<SuccessResponse<Any?>> {
        signupUseCase.signup(
            SignupCommand(
                username = request.username,
                password = request.password,
                confirmPassword = request.confirmPassword
            )
        )
        return ResponseEntityFactory.success()
    }

}

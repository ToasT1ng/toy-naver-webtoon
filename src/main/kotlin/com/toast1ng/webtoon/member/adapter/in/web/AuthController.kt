package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.request.LoginRequest
import com.toast1ng.webtoon.member.adapter.`in`.web.request.SignupRequest
import com.toast1ng.webtoon.member.application.port.`in`.LoginUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.SignupUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import com.toast1ng.webtoon.member.application.port.`in`.command.SignupCommand
import com.toast1ng.webtoon.member.domain.UserRole
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val signupUseCase: SignupUserUseCase,
    private val loginUseCase: LoginUserUseCase
) {

    //TODO: Controller 분리
    //TODO: 비밀번호 2번 입력 받도록 수정
    @PostMapping("/auth/signup")
    fun signup(@Valid @RequestBody request: SignupRequest): ResponseEntity<SuccessResponse<Any?>> {
        signupUseCase.signup(
            SignupCommand(
                username = request.username,
                password = request.password,
                role = UserRole.USER //TODO: UserRole request로 부터 치환
            )
        )
        return ResponseEntityFactory.success()
    }

    @PostMapping("/auth/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<SuccessResponse<Any?>> {
        loginUseCase.login(
            LoginCommand(
                username = request.username,
                password = request.password
            )
        )
        return ResponseEntityFactory.success()
   }
}

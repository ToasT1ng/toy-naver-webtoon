package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.SignupUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.SignupCommand
import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.application.port.out.RegisterUserPort
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserFactory
import com.toast1ng.webtoon.member.domain.error.UserAuthErrorResponseCode
import com.toast1ng.webtoon.member.domain.error.UserAuthException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupService(
    private val passwordEncoder: PasswordEncoder,
    private val readUserPort: ReadUserPort,
    private val registerUserPort: RegisterUserPort,
) : SignupUserUseCase {
    override fun signup(command: SignupCommand): User {
        if (command.password != command.confirmPassword) {
            throw UserAuthException(UserAuthErrorResponseCode.PASSWORD_MISMATCH)
        }
        if (readUserPort.isUserExists(UserQuery(username = command.username))) {
            throw UserAuthException(UserAuthErrorResponseCode.USER_ALREADY_EXISTS)
        }
        val encodedPassword = passwordEncoder.encode(command.password)
        return registerUserPort.registerUser(
            UserFactory.create(
                username = command.username,
                password = encodedPassword,
            )
        )
    }
}
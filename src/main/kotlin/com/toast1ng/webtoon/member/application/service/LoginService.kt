package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.LoginUserUseCase
import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery
import com.toast1ng.webtoon.member.domain.error.UserAuthErrorResponseCode
import com.toast1ng.webtoon.member.domain.error.UserAuthException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val readUserPort: ReadUserPort,
    private val passwordEncoder: PasswordEncoder,
) : LoginUserUseCase {
    override fun login(command: LoginCommand) {
        val user = readUserPort.getUser(UserQuery(username = command.username))
            ?: throw UserAuthException(UserAuthErrorResponseCode.USER_NOT_FOUND)
        if (!passwordEncoder.matches(command.password, user.password)) {
            throw UserAuthException(UserAuthErrorResponseCode.INVALID_PASSWORD)
        }
    }
}


package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import com.toast1ng.webtoon.member.domain.User

interface LoginUserUseCase {
    fun login(command: LoginCommand): User
}


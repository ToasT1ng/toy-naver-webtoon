package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand

interface LoginUserUseCase {
    fun login(command: LoginCommand)
}


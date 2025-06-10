package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.application.port.`in`.command.SignupCommand
import com.toast1ng.webtoon.member.domain.User

interface SignupUserUseCase {
    fun signup(command: SignupCommand): User
}
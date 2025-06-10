package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.domain.User

interface RegisterUserPort {
    fun registerUser(user: User): User
}
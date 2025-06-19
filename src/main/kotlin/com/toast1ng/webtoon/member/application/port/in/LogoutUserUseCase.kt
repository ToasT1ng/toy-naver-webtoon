package com.toast1ng.webtoon.member.application.port.`in`

interface LogoutUserUseCase {
    fun logout(accessToken: String)
}
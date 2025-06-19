package com.toast1ng.webtoon.member.application.port.`in`

interface RefreshTokenUseCase {
    fun refreshAccessToken(refreshToken: String): String
}


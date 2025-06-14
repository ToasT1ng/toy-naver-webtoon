package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.Tokens

interface TokenUseCase {
    fun makeTokens(username: String, isAutoLogin: Boolean): Tokens
    fun makeAccessToken(username: String): String
}
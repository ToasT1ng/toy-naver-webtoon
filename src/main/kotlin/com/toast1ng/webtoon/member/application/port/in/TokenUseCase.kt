package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.Tokens

interface TokenUseCase {
    fun makeTokens(id: Long, isAutoLogin: Boolean): Tokens
    fun makeAccessToken(id: Long): String
}
package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.Tokens

interface MakeTokenUseCase {
    fun makeTokens(id: Long, isAutoLogin: Boolean): Tokens
}
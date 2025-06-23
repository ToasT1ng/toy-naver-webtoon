package com.toast1ng.webtoon.member.application.port.`in`

interface QueryTokenUseCase{
    fun validateTokenIsWhiteListed(refreshToken: String)
    fun validateTokenIsNotBlackListed(accessToken: String)
}
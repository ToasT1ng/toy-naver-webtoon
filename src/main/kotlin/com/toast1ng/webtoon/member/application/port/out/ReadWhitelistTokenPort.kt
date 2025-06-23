package com.toast1ng.webtoon.member.application.port.out

interface ReadWhitelistTokenPort {
    fun isWhitelistedToken(token: String, userId: Long): Boolean
}
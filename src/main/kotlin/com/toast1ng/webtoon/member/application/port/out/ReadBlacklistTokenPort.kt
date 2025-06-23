package com.toast1ng.webtoon.member.application.port.out

interface ReadBlacklistTokenPort {
    fun isBlacklistedToken(accessToken: String): Boolean
}
package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.application.port.`in`.command.StoreWhitelistTokenCommand

interface StoreTokenUseCase {
    fun storeWhitelistToken(command: StoreWhitelistTokenCommand)
    fun storeBlacklistToken(accessToken: String)
}
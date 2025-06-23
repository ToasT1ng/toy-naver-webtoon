package com.toast1ng.webtoon.member.application.port.`in`.command

data class StoreWhitelistTokenCommand(
    val refreshToken: String,
    val userId: Long,
    val ip: String? = null,
    val device: String? = null,
)

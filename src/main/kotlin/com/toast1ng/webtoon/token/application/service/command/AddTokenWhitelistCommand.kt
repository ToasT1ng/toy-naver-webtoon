package com.toast1ng.webtoon.token.application.service.command

data class AddTokenWhitelistCommand(
    val refreshToken: String,
    val userId: String,
    val ip: String,
    val device: String,
    val ttlSeconds: Long
)
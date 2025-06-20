package com.toast1ng.webtoon.token.application.service.command

data class AddTokenBlacklistCommand(
    val accessToken: String,
    val ttlSeconds: Long
)
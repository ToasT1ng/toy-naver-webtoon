package com.toast1ng.webtoon.member.application.port.out.cache

data class WhitelistCache(
    val token: String,
    val userId: String,
    val ip: String,
    val device: String,
    val ttlSeconds: Long
)
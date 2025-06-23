package com.toast1ng.webtoon.member.application.port.out.cache

data class BlacklistCache(
    val accessToken: String,
    val ttlSeconds: Long
)
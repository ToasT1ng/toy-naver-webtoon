package com.toast1ng.webtoon.token.application.service

import com.toast1ng.webtoon.token.application.service.command.AddTokenBlacklistCommand
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AccessTokenBlacklistService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val valueOps: ValueOperations<String, String> = redisTemplate.opsForValue()

    fun add(command: AddTokenBlacklistCommand) {
        val key = "access:${command.accessToken}"
        valueOps.set(key, "blacklisted", command.ttlSeconds, TimeUnit.SECONDS)
    }

    fun isBlacklisted(accessToken: String): Boolean {
        val key = "access:$accessToken"
        return redisTemplate.hasKey(key)
    }
}
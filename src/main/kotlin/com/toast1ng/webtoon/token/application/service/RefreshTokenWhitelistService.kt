package com.toast1ng.webtoon.token.application.service

import com.toast1ng.webtoon.token.application.service.command.AddTokenWhitelistCommand
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RefreshTokenWhitelistService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    private val hashOps: HashOperations<String, String, Any> = redisTemplate.opsForHash()

    fun add(command: AddTokenWhitelistCommand) {
        val key = "refresh:${command.refreshToken}"
        hashOps.put(key, "userId", command.userId)
        hashOps.put(key, "ip", command.ip)
        hashOps.put(key, "device", command.device)
        redisTemplate.expire(key, command.ttlSeconds, TimeUnit.SECONDS)
    }

    fun getUserId(refreshToken: String): String? {
        val key = "refresh:$refreshToken"
        return hashOps.get(key, "userId")?.toString()
    }

    fun delete(refreshToken: String) {
        redisTemplate.delete("refresh:$refreshToken")
    }
}
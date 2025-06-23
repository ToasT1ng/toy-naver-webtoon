package com.toast1ng.webtoon.member.adapter.out.redis

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.member.application.port.out.ReadBlacklistTokenPort
import com.toast1ng.webtoon.member.application.port.out.StoreBlacklistTokenPort
import com.toast1ng.webtoon.member.application.port.out.cache.BlacklistCache
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import java.util.concurrent.TimeUnit

@OutgoingAdapter
class RedisAccessTokenBlacklistService(
    private val redisTemplate: RedisTemplate<String, String>
) : StoreBlacklistTokenPort, ReadBlacklistTokenPort {
    private val valueOps: ValueOperations<String, String> = redisTemplate.opsForValue()

    override fun add(command: BlacklistCache) {
        val key = "access:${command.accessToken}"
        valueOps.set(key, "blacklisted", command.ttlSeconds, TimeUnit.SECONDS)
    }

    override fun isBlacklistedToken(accessToken: String): Boolean {
        val key = "access:$accessToken"
        return redisTemplate.hasKey(key)
    }
}
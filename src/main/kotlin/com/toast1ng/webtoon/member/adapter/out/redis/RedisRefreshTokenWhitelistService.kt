package com.toast1ng.webtoon.member.adapter.out.redis

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.member.application.port.out.ReadWhitelistTokenPort
import com.toast1ng.webtoon.member.application.port.out.StoreWhitelistTokenPort
import com.toast1ng.webtoon.member.application.port.out.cache.WhitelistCache
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@OutgoingAdapter
class RedisRefreshTokenWhitelistService(
    private val redisTemplate: RedisTemplate<String, Any>
) : StoreWhitelistTokenPort, ReadWhitelistTokenPort {
    private val hashOps: HashOperations<String, String, Any> = redisTemplate.opsForHash()

    override fun add(command: WhitelistCache) {
        val key = "refresh:${command.token}"
        hashOps.put(key, "userId", command.userId)
        hashOps.put(key, "ip", command.ip)
        hashOps.put(key, "device", command.device)
        redisTemplate.expire(key, command.ttlSeconds, TimeUnit.SECONDS)
    }

    override fun isWhitelistedToken(token: String, userId: Long): Boolean {
        val key = "refresh:$token"
        return hashOps.hasKey(key, "userId") && hashOps.get(key, "userId") == userId.toString()
    }

//
//    override fun delete(token: String) {
//        redisTemplate.delete("refresh:$token")
//    }
}
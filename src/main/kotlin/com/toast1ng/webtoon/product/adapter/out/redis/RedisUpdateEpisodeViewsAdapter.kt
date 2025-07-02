package com.toast1ng.webtoon.product.adapter.out.redis

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.common.utils.toDateHourFormat
import com.toast1ng.webtoon.product.application.port.out.UpdateEpisodeViewsPort
import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@OutgoingAdapter
class RedisUpdateEpisodeViewsAdapter(
    private val redisTemplate: RedisTemplate<String, String>
) : UpdateEpisodeViewsPort {
    override fun increaseEpisodeViews(episodeId: Long) {
        val baseKey = buildKeyForCurrentHour()
        increaseViewCount("$baseKey:episode", episodeId)
    }

    override fun increaseWebtoonViews(webtoonId: Long) {
        val baseKey = buildKeyForCurrentHour()
        increaseViewCount("$baseKey:webtoon", webtoonId)
    }

    private fun increaseViewCount(key: String, id: Long) {
        redisTemplate.opsForZSet().incrementScore(key, id.toString(), 1.0)
        redisTemplate.expire(key, RedisEpisodeViewsConst.TTL_SECONDS, TimeUnit.SECONDS)
    }

    private fun buildKeyForCurrentHour(): String {
        val now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
        return "${RedisEpisodeViewsConst.VIEW_KEY_PREFIX}:${now.toDateHourFormat()}"
    }
}
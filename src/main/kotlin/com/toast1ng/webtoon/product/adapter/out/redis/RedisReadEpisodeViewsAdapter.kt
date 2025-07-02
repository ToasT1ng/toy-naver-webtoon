package com.toast1ng.webtoon.product.adapter.out.redis

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.common.utils.toDateHourFormat
import com.toast1ng.webtoon.product.application.port.out.ReadEpisodeViewsPort
import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@OutgoingAdapter
class RedisReadEpisodeViewsAdapter(
    private val redisTemplate: RedisTemplate<String, String>
) : ReadEpisodeViewsPort {
    override fun getWebtoonRanking(
        webtoonId: Long,
        baseHour: Int
    ): List<Pair<Long, Int>> {
        return getTopRanked("episode", baseHour)
    }

    override fun getEpisodeRanking(
        episodeId: Long,
        baseHour: Int
    ): List<Pair<Long, Int>> {
        return getTopRanked("webtoon", baseHour)
    }

    private fun getTopRanked(type: String, baseHour: Int): List<Pair<Long, Int>> {
        val keys = getKeysForLastNHours(baseHour).map { "$it:$type" }
        val scoreMap = mutableMapOf<Long, Double>()

        keys.forEach { key ->
            val entries = redisTemplate.opsForZSet().rangeWithScores(key, 0, -1)
            entries?.forEach { tuple ->
                val id = tuple.value?.toLong()
                id?.let {
                    scoreMap[id] = scoreMap.getOrDefault(id, 0.0) + (tuple.score ?: 0.0)
                }
            }
        }

        return scoreMap.entries
            .sortedByDescending { it.value }
            .map { it.key to it.value.toInt() }
    }

    private fun getKeysForLastNHours(baseHour: Int): List<String> {
        val now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
        return (0..baseHour-1).map {
            val time = now.minusHours(it.toLong())
            "${RedisEpisodeViewsConst.VIEW_KEY_PREFIX}:${time.toDateHourFormat()}"
        }
    }
}
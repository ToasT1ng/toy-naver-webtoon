package com.toast1ng.webtoon.product.adapter.out.redis

import java.time.Duration

object RedisEpisodeViewsConst {
    const val VIEW_KEY_PREFIX = "episode:view"
    val TTL_SECONDS: Long = Duration.ofHours(3).seconds
}
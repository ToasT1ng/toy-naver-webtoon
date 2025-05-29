package com.toast1ng.webtoon.product.application.port.out.query

import java.time.LocalDateTime

data class WebtoonEpisodeNavigationQuery(
    val webtoonId: Long,
    val currentEpisodeId: Long,
    val uploadDateTo: LocalDateTime,
)

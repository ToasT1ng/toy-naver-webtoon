package com.toast1ng.webtoon.product.application.port.out.query

import java.time.LocalDateTime

data class WebtoonEpisodeQuery(
    val id: Long? = null,
    val webtoonId: Long? = null,
    val uploadDateTo: LocalDateTime? = null,
)

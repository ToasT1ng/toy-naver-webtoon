package com.toast1ng.webtoon.product.application.port.out

import java.time.LocalDateTime

data class GetWebtoonEpisodeQuery(
    val id: Long? = null,
    val webtoonId: Long? = null,
    val uploadDateTo: LocalDateTime? = null,
)

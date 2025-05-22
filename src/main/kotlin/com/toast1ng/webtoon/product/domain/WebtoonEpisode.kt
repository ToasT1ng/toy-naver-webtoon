package com.toast1ng.webtoon.product.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class WebtoonEpisode(
    val id: Long,
    val title: String,
    val episodeNumber: Int,
    val episodeTitle: String,
    val rating: BigDecimal,
    val thumbnail: String,
    val uploadDate: LocalDateTime,
    val images: List<String>,
    val webtoon: WebtoonProductBriefInfo,
) {
    data class WebtoonProductBriefInfo(
        val id: Long,
        val title: String,
    )
}
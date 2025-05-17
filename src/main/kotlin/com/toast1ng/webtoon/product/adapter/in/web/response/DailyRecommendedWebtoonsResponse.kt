package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class DailyRecommendedWebtoonsResponse(
    val result: List<DailyRecommendedWebtoonResponse>
) {
    data class DailyRecommendedWebtoonResponse(
        val id: Long,
        val title: String,
        val thumbnail: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val latestEpisodeName: String,
    )
}

fun WebtoonProduct.toDailyRecommendedWebtoonResponse(): DailyRecommendedWebtoonsResponse.DailyRecommendedWebtoonResponse {
    return DailyRecommendedWebtoonsResponse.DailyRecommendedWebtoonResponse(
        id = this.id,
        title = this.title,
        thumbnail = this.subThumbnail,
        authorIds = this.creators.map { it.personId },
        authorNames = this.creators.joinToString(", ") { it.name },
        latestEpisodeName = "1화",
    )
}
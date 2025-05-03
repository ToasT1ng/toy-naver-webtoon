package com.toast1ng.webtoon.product.adapter.`in`.web.response

data class DailyRecommendedWebtoonsResponse(
    val result: List<DailyRecommendedWebtoonResponse>
) {
    data class DailyRecommendedWebtoonResponse(
        val id: Long,
        val title: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val latestEpisodeName: String,
        val rating: String,
    )
}
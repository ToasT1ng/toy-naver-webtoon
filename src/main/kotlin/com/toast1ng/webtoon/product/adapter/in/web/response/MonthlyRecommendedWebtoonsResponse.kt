package com.toast1ng.webtoon.product.adapter.`in`.web.response

data class MonthlyRecommendedWebtoonsResponse(
    val result: List<MonthlyRecommendedWebtoonResponse>
) {
    data class MonthlyRecommendedWebtoonResponse(
        val id: Long,
        val title: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val description: String,
    )
}
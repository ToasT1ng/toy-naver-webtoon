package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class MonthlyRecommendedWebtoonsResponse(
    val result: List<MonthlyRecommendedWebtoonResponse>
) {
    data class MonthlyRecommendedWebtoonResponse(
        val id: Long,
        val title: String,
        val thumbnail: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val description: String,
    )
}

fun WebtoonProduct.toMonthlyRecommendedWebtoonResponse(): MonthlyRecommendedWebtoonsResponse.MonthlyRecommendedWebtoonResponse {
    return MonthlyRecommendedWebtoonsResponse.MonthlyRecommendedWebtoonResponse(
        id = this.id,
        title = this.title,
        thumbnail = this.subThumbnail,
        authorIds = this.creators.map { it.personId },
        authorNames = this.creators.joinToString(", ") { it.name },
        description = this.description,
    )
}
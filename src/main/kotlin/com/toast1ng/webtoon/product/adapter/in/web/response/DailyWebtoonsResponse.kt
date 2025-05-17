package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class DailyWebtoonsResponse(
    val result: List<DailyWebtoonResponse>
) {
    data class DailyWebtoonResponse(
        val id: Long,
        val title: String,
        val thumbnail: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val rating: String,
    )
}

fun WebtoonProduct.toDailyWebtoonResponse(): DailyWebtoonsResponse.DailyWebtoonResponse {
    return DailyWebtoonsResponse.DailyWebtoonResponse(
        id = this.id,
        title = this.title,
        thumbnail = this.mainThumbnail,
        authorIds = this.creators.map { it.personId },
        authorNames = this.creators.joinToString(", ") { it.name },
        rating = this.rating.toString(),
    )
}
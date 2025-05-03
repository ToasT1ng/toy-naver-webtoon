package com.toast1ng.webtoon.product.adapter.`in`.web.response

data class DailyWebtoonsResponse(
    val result: List<DailyWebtoonResponse>
) {
    data class DailyWebtoonResponse(
        val id: Long,
        val title: String,
        val authorIds: List<Long>,
        val authorNames: String,
        val rating: String,
    )
}
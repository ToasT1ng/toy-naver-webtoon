package com.toast1ng.webtoon.product.adapter.`in`.web.response

data class EveryWebtoonResponse(
    val result: Map<String, List<EachWebtoonResponse>>
) {
    data class EachWebtoonResponse(
        val id: Long,
        val title: String,
    )
}
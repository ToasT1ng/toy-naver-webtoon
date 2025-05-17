package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class EveryWebtoonResponse(
    val result: Map<String, List<EachWebtoonResponse>>
) {
    data class EachWebtoonResponse(
        val id: Long,
        val title: String,
        val thumbnail: String,
    )
}

fun WebtoonProduct.toEachWebtoonResponse(): EveryWebtoonResponse.EachWebtoonResponse {
    return EveryWebtoonResponse.EachWebtoonResponse(
        id = this.id,
        title = this.title,
        thumbnail = this.mainThumbnail,
    )
}
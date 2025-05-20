package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class WebtoonResponse(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val authorIds: List<Long>,
    val authorNames: String,
    val genres: List<String>,
    val likesCount: Int,
)

fun WebtoonProduct.toWebtoonResponse(): WebtoonResponse {
    return WebtoonResponse(
        id = this.id,
        title = this.title,
        thumbnail = this.mainThumbnail,
        authorIds = this.creators.map { it.personId },
        authorNames = this.creators.joinToString(", ") { it.name },
        genres = emptyList(), // TODO: Implement genre mapping
        likesCount = 0, // TODO: Implement likes count mapping
    )
}
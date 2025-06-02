package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.Creator
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class WebtoonResponse(
    val id: Long,
    val title: String,
    val writer: CreatorResponse,
    val illustrator: CreatorResponse,
    val originalAuthor: CreatorResponse?,
    val dayOfWeek: DayOfWeek,
    val restrictedAge: Int,
    val description: String,
    val tags: List<String>,
    val thumbnail: String,
    val likesCount: Int,
) {
    data class CreatorResponse(
        val personId: Long,
        val name: String,
    )
}

fun WebtoonProduct.toWebtoonResponse(): WebtoonResponse {
    return WebtoonResponse(
        id = this.id,
        title = this.title,
        writer = this.writer.toPersonResponse(),
        illustrator = this.illustrator.toPersonResponse(),
        originalAuthor = this.originalAuthor?.toPersonResponse(),
        dayOfWeek = DayOfWeek.SATURDAY, // TODO: Implement dayOfWeek mapping
        restrictedAge = 77, // TODO: Implement restrictedAge mapping
        description = this.description,
        thumbnail = this.mainThumbnail,
        tags = this.genres.map { it.name },
        likesCount = this.likes,
    )
}

fun Creator.toPersonResponse(): WebtoonResponse.CreatorResponse {
    return WebtoonResponse.CreatorResponse(
        personId = this.personId,
        name = this.name,
    )
}
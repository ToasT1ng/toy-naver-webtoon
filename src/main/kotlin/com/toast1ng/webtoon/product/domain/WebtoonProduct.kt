package com.toast1ng.webtoon.product.domain

import java.math.BigDecimal

data class WebtoonProduct(
    val id: Long,
    val title: String,
    val description: String,
    val views: Int,
    val rating: BigDecimal,
    val likes: Int,
    val mainThumbnail: String,
    val subThumbnail: String,
    val day: DayOfWeek,
    val status: WebtoonStatus,
    val genres: List<Genre>,
    val creators: List<Creator>,
) {
    private val creatorMap = creators.associateBy { it.role }

    val writer: Creator = requireNotNull(creatorMap[CreatorRole.WRITER]) { "WRITER is required" }
    val illustrator: Creator = requireNotNull(creatorMap[CreatorRole.ILLUSTRATOR]) { "ILLUSTRATOR is required" }
    val originalAuthor: Creator? = creatorMap[CreatorRole.ORIGINAL_AUTHOR]
}
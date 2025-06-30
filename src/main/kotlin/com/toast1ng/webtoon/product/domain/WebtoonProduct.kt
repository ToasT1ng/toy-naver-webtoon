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
    val restrictedAge: Int,
    val status: WebtoonStatus,
    val genres: List<Genre>,
    val creators: List<Creator>,
) {
    private val creatorMap = creators.associateBy { it.role }

    fun getWriter(): Creator {
        return requireNotNull(creatorMap[CreatorRole.WRITER]) { "WRITER is required" }
    }

    fun getIllustrator(): Creator {
        return requireNotNull(creatorMap[CreatorRole.ILLUSTRATOR]) { "ILLUSTRATOR is required" }
    }

    fun getOriginalAuthor(): Creator? {
        return creatorMap[CreatorRole.ORIGINAL_AUTHOR]
    }
}
package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import java.math.BigDecimal
import java.sql.Timestamp

data class WebtoonWithPersonDto(
    val id: Long,
    val title: String,
    val description: String,
    val genreId: Long,
    val views: Int,
    val rating: BigDecimal,
    val mainThumbnailLink: String?,
    val subThumbnailLink: String?,
    val dayOfWeek: String,
    val status: String,
    val isDeleted: Boolean,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val creators: List<CreatorDto>
)

data class CreatorDto(
    val personId: Long,
    val name: String,
    val role: String
)
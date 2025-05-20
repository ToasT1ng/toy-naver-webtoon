package com.toast1ng.webtoon.product.domain

import java.math.BigDecimal

data class WebtoonProduct(
    val id: Long,
    val title: String,
    val description: String,
    val views: Int,
    val rating: BigDecimal,
    val mainThumbnail: String,
    val subThumbnail: String,
    val day: DayOfWeek,
    val status: WebtoonStatus,
    val genres: List<Genre>,
    val creators: List<Creator>,
)
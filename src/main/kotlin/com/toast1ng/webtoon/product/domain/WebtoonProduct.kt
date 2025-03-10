package com.toast1ng.webtoon.product.domain

import java.math.BigDecimal
import java.time.DayOfWeek

data class WebtoonProduct(
    val id: String,
    val title: String,
    val authorId: String,
    val views: Int,
    val rating: BigDecimal,
    val day: DayOfWeek,
    val status: WebtoonStatus
)
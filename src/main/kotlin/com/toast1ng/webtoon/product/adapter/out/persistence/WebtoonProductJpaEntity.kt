package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.domain.WebtoonStatus
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

data class WebtoonProductJpaEntity(
    val id: String,
    val title: String,
    val authorId: String,
    val views: Int,
    val rating: BigDecimal,
    val updatedDate: LocalDateTime,
    val day: DayOfWeek,
    val status: WebtoonStatus,
    val isDeleteFlag: Boolean
)
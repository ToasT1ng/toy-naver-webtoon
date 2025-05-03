package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonStatus
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table(name = "webtoons")
@Entity
class WebtoonProductJpaEntity(
    @Id
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
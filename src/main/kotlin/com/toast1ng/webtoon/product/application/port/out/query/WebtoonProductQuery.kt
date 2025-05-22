package com.toast1ng.webtoon.product.application.port.out.query

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class WebtoonProductQuery(
    val id: Long? = null,
    val day: DayOfWeek? = null,
)

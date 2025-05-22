package com.toast1ng.webtoon.product.application.port.out.query

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class ThreeWebtoonsQuery(
    val day: DayOfWeek? = null,
    val randomRowNumbers: List<Long>,
)

package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class GetThreeWebtoonsQuery(
    val day: DayOfWeek? = null,
    val randomRowNumbers: List<Long>,
)

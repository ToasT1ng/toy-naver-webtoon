package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class GetWebtoonsQuery(
    val day: DayOfWeek? = null,
)

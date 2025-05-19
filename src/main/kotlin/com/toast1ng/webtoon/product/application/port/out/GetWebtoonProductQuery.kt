package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class GetWebtoonProductQuery(
    val id: Long? = null,
    val day: DayOfWeek? = null,
)

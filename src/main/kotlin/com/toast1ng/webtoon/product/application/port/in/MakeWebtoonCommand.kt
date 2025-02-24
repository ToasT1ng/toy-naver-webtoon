package com.toast1ng.webtoon.product.application.port.`in`

import java.time.DayOfWeek

data class MakeWebtoonCommand(
    val title: String,
    val authorId: String,
    val day: DayOfWeek
)
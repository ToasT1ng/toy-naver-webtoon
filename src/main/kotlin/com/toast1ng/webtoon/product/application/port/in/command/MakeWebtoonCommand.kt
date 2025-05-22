package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.product.domain.DayOfWeek

data class MakeWebtoonCommand(
    val title: String,
    val authorId: String,
    val day: DayOfWeek
)
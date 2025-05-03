package com.toast1ng.webtoon.product.adapter.`in`.web.request

import com.toast1ng.webtoon.product.domain.DayOfWeek


data class WriteWebtoonVO (
    var title: String,
    var authorId: String,
    var day: DayOfWeek,
)
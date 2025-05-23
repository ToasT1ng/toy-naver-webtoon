package com.toast1ng.webtoon.common.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val DATE_ONLY_FORMAT = "yyyy-MM-dd"

fun LocalDateTime.toDateOnlyFormatString(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_ONLY_FORMAT))
}
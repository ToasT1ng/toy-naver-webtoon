package com.toast1ng.webtoon.common.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

const val DATE_ONLY_FORMAT = "yyyy-MM-dd"

fun LocalDateTime.toDateOnlyFormatString(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_ONLY_FORMAT))
}


fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}
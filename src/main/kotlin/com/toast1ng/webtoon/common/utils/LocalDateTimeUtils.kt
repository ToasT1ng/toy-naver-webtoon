package com.toast1ng.webtoon.common.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

const val DATE_ONLY_FORMAT_WITH_HYPHEN = "yyyy-MM-dd"
const val DATE_HOUR_FORMAT = "yyyyMMddHH"

fun LocalDateTime.toDateOnlyFormatWithHyphenString(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_ONLY_FORMAT_WITH_HYPHEN))
}

fun LocalDateTime.toDateHourFormat(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_HOUR_FORMAT))
}

fun LocalDateTime.toFormattedString(format: String): String {
    return this.format(DateTimeFormatter.ofPattern(format))
}

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}

fun Date.toLocalDateTime(): LocalDateTime {
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}
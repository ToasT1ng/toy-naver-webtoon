package com.toast1ng.webtoon.product.domain

enum class DayOfWeek(val value: String) {
    MONDAY("mon"),
    TUESDAY("tue"),
    WEDNESDAY("wed"),
    THURSDAY("thu"),
    FRIDAY("fri"),
    SATURDAY("sat"),
    SUNDAY("sun");

    companion object {
        fun fromValue(value: String): DayOfWeek {
            return DayOfWeek.entries.find { it.value == value }
                ?: throw IllegalArgumentException("Invalid day of week: $value")
        }
    }
}
package com.toast1ng.webtoon.common.utils

import com.toast1ng.webtoon.common.SortDirection

fun SortDirection.toJpaSortDirection(): org.springframework.data.domain.Sort.Direction {
    return when (this) {
        SortDirection.ASC -> org.springframework.data.domain.Sort.Direction.ASC
        SortDirection.DESC -> org.springframework.data.domain.Sort.Direction.DESC
    }
}
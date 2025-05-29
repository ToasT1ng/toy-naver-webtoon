package com.toast1ng.webtoon.test.utils

import io.mockk.every
import java.time.LocalDateTime

fun mockLocalDateTimeTo(fixed: LocalDateTime) {
    every { LocalDateTime.now() } returns fixed
}
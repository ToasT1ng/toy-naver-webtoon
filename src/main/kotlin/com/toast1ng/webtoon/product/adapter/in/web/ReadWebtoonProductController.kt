package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.response.DailyWebtoonsResponse
import com.toast1ng.webtoon.product.domain.DayOfWeek
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonProductController() {

    @GetMapping("/webtoons/daily")
    fun getDailyWebtoons(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<DailyWebtoonsResponse> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        return ResponseEntity.ok().body(
            DailyWebtoonsResponse(
                result = listOf(
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 1L,
                        title = "Test Webtoon",
                        authorIds = listOf(1L, 2L),
                        authorNames = "Author1, Author2",
                        rating = "9.5"
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 2L,
                        title = "Another Webtoon",
                        authorIds = listOf(3L),
                        authorNames = "Author3",
                        rating = "8.7"
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 3L,
                        title = "Third Webtoon",
                        authorIds = listOf(4L),
                        authorNames = "Author4",
                        rating = "8.9"
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 4L,
                        title = "Fourth Webtoon",
                        authorIds = listOf(5L, 6L),
                        authorNames = "Author5, Author6",
                        rating = "9.2"
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 5L,
                        title = "Fifth Webtoon",
                        authorIds = listOf(7L),
                        authorNames = "Author7",
                        rating = "8.8"
                    )
                )
            )
        )
    }
}


package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.response.DailyRecommendedWebtoonsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.DailyWebtoonsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.MonthlyRecommendedWebtoonsResponse
import com.toast1ng.webtoon.product.domain.DayOfWeek
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonProductController() {
    @GetMapping("/webtoons/daily/recommend/three")
    fun getDailyRecommendedWebtoon(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<DailyRecommendedWebtoonsResponse> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        return ResponseEntity.ok().body(
            DailyRecommendedWebtoonsResponse(
                result = listOf(
                    DailyRecommendedWebtoonsResponse.DailyRecommendedWebtoonResponse(
                        id = 11L,
                        title = "Test Recommend Webtoon Really Really Really Really Long Title",
                        authorIds = listOf(1L, 2L),
                        authorNames = "Author1, Author2",
                        latestEpisodeName = "Episode 10",
                        rating = "9.5"
                    ),
                    DailyRecommendedWebtoonsResponse.DailyRecommendedWebtoonResponse(
                        id = 12L,
                        title = "Another Recommend Webtoon",
                        authorIds = listOf(3L),
                        authorNames = "Author3",
                        latestEpisodeName = "Episode 5",
                        rating = "8.7"
                    ),
                    DailyRecommendedWebtoonsResponse.DailyRecommendedWebtoonResponse(
                        id = 13L,
                        title = "Third Recommend Webtoon",
                        authorIds = listOf(4L),
                        authorNames = "Author4",
                        latestEpisodeName = "Episode 8",
                        rating = "8.9"
                    )
                )
            )
        )
    }


    @GetMapping("/webtoons/daily")
    fun getDailyWebtoons(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<DailyWebtoonsResponse> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        return ResponseEntity.ok().body(
            DailyWebtoonsResponse(
                result = listOf(
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 1L,
                        title = "Test Webtoon Really Really Really Really Long Title",
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
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 6L,
                        title = "Sixth Webtoon",
                        authorIds = listOf(8L),
                        authorNames = "Author8",
                        rating = "9.0"
                    ),
                    DailyWebtoonsResponse.DailyWebtoonResponse(
                        id = 7L,
                        title = "Seventh Webtoon",
                        authorIds = listOf(9L, 10L),
                        authorNames = "Author9, Author10",
                        rating = "9.3"
                    )
                )
            )
        )
    }

    @GetMapping("/webtoons/monthly/recommend/three")
    fun getMonthlyRecommendedWebtoon(): ResponseEntity<MonthlyRecommendedWebtoonsResponse> {
        return ResponseEntity.ok().body(
            MonthlyRecommendedWebtoonsResponse(
                result = listOf(
                    MonthlyRecommendedWebtoonsResponse.MonthlyRecommendedWebtoonResponse(
                        id = 15L,
                        title = "Test Monthly New Webtoon Really Really Really Really Long Title",
                        authorIds = listOf(10L, 20L),
                        authorNames = "RandomAuthor1, RandomAuthor2",
                        description = "This is some description for the webtoon.",
                    ),
                    MonthlyRecommendedWebtoonsResponse.MonthlyRecommendedWebtoonResponse(
                        id = 16L,
                        title = "Another Monthly New",
                        authorIds = listOf(30L),
                        authorNames = "RandomAuthor3",
                        description = "This is a test webtoon description that is really really long and goes on and on and on. It should be long enough to test the truncation logic.",
                    ),
                    MonthlyRecommendedWebtoonsResponse.MonthlyRecommendedWebtoonResponse(
                        id = 17L,
                        title = "Third Monthly New",
                        authorIds = listOf(40L, 50L),
                        authorNames = "RandomAuthor4, RandomAuthor5",
                        description = "This is some description for the webtoon.",
                    ),
                )
            )
        )
    }
}


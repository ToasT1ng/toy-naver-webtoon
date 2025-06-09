package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.SortDirection
import com.toast1ng.webtoon.common.SortOption
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.*
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetSortedDailyWebtoonsCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.WebtoonProductSortKey
import com.toast1ng.webtoon.product.domain.DayOfWeek
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonProductController(
    private val getWebtoonUseCase: GetWebtoonUseCase,
) {
    @GetMapping("/webtoons/daily/recommend/three")
    fun getDailyRecommendedWebtoon(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<SuccessResponse<DailyRecommendedWebtoonsResponse>> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        val result = getWebtoonUseCase.getRandomThreeWebtoons(dayOfWeek)
        return ResponseEntityFactory.success(
            DailyRecommendedWebtoonsResponse(
                result = result.map { it.toDailyRecommendedWebtoonResponse() }
            )
        )
    }


    @GetMapping("/webtoons/daily")
    fun getDailyWebtoons(
        @RequestParam("dayOfWeek") dayOfWeekValue: String,
        @RequestParam("orderBy") sortKey: WebtoonProductSortKey = WebtoonProductSortKey.POPULAR,
        @RequestParam("direction") direction: SortDirection = SortDirection.DESC
    ): ResponseEntity<SuccessResponse<DailyWebtoonsResponse>> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        val result = getWebtoonUseCase.getDailyWebtoons(
            GetSortedDailyWebtoonsCommand(
                day = dayOfWeek,
                sortOption = SortOption(
                    key = sortKey,
                    direction = direction
                )
            )
        )
        return ResponseEntityFactory.success(
            DailyWebtoonsResponse(
                result = result.map { it.toDailyWebtoonResponse() }
            )
        )
    }

    @GetMapping("/webtoons/monthly/recommend/three")
    fun getMonthlyRecommendedWebtoon(): ResponseEntity<SuccessResponse<MonthlyRecommendedWebtoonsResponse>> {
        val result = getWebtoonUseCase.getRandomThreeWebtoons()
        return ResponseEntityFactory.success(
            MonthlyRecommendedWebtoonsResponse(
                result = result.map { it.toMonthlyRecommendedWebtoonResponse() }
            )
        )
    }

    @GetMapping("/webtoons")
    fun getWebtoons(): ResponseEntity<SuccessResponse<EveryWebtoonResponse>> {
        val result = getWebtoonUseCase.getEveryWebtoons()
        return ResponseEntityFactory.success(
            EveryWebtoonResponse(
                result = result
                    .groupBy { it.day.value }
                    .mapValues { (_, list) -> list.map { it.toEachWebtoonResponse() } }
            )
        )
    }

    @GetMapping("/webtoons/{id}")
    fun getWebtoon(@PathVariable id: Long): ResponseEntity<SuccessResponse<WebtoonResponse>> {
        val result = getWebtoonUseCase.getWebtoon(id)
        return ResponseEntityFactory.success(
            result.toWebtoonResponse()
        )
    }
}


package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.response.DailyRecommendedWebtoonsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.DailyWebtoonsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.EveryWebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.MonthlyRecommendedWebtoonsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.WebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toDailyRecommendedWebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toDailyWebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toEachWebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toMonthlyRecommendedWebtoonResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toWebtoonResponse
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
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
    fun getDailyRecommendedWebtoon(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<DailyRecommendedWebtoonsResponse> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        val result = getWebtoonUseCase.getRandomThreeWebtoons(dayOfWeek)
        return ResponseEntity.ok().body(
            DailyRecommendedWebtoonsResponse(
                result = result.map { it.toDailyRecommendedWebtoonResponse() }
            )
        )
    }


    @GetMapping("/webtoons/daily")
    fun getDailyWebtoons(@RequestParam("dayOfWeek") dayOfWeekValue: String): ResponseEntity<DailyWebtoonsResponse> {
        val dayOfWeek = DayOfWeek.fromValue(dayOfWeekValue)
        val result = getWebtoonUseCase.getWebtoons(dayOfWeek)
        return ResponseEntity.ok().body(
            DailyWebtoonsResponse(
                result = result.map { it.toDailyWebtoonResponse() }
            )
        )
    }

    @GetMapping("/webtoons/monthly/recommend/three")
    fun getMonthlyRecommendedWebtoon(): ResponseEntity<MonthlyRecommendedWebtoonsResponse> {
        val result = getWebtoonUseCase.getRandomThreeWebtoons()
        return ResponseEntity.ok().body(
            MonthlyRecommendedWebtoonsResponse(
                result = result.map { it.toMonthlyRecommendedWebtoonResponse() }
            )
        )
    }

    @GetMapping("/webtoons")
    fun getWebtoons(): ResponseEntity<EveryWebtoonResponse> {
        val result = getWebtoonUseCase.getEveryWebtoons()
        return ResponseEntity.ok().body(
            EveryWebtoonResponse(
                result = result
                    .groupBy { it.day.value }
                    .mapValues { (_, list) -> list.map { it.toEachWebtoonResponse() } }
            )
        )
    }

    @GetMapping("/webtoons/{id}")
    fun getWebtoon(@PathVariable id: Long): ResponseEntity<WebtoonResponse> {
        val result = getWebtoonUseCase.getWebtoon(id)
        return ResponseEntity.ok().body(
            result.toWebtoonResponse()
        )
    }
}


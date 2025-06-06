package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.domain.WebtoonProduct
import com.toast1ng.webtoon.product.domain.DayOfWeek

interface GetWebtoonUseCase {
    fun getWebtoon(webtoonId: Long): WebtoonProduct
    fun getDailyWebtoons(day: DayOfWeek): List<WebtoonProduct>
    fun getEveryWebtoons(): List<WebtoonProduct>
    fun getRandomThreeWebtoons(day: DayOfWeek? = null): List<WebtoonProduct>
}
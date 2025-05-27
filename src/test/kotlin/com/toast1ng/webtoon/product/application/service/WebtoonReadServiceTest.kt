package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
import com.toast1ng.webtoon.product.domain.DayOfWeek
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebtoonReadServiceTest @Autowired constructor(
    private val webtoonReadService: GetWebtoonUseCase,
) {
    @DisplayName("웹툰 1개 불러오기")
    @Test
    fun getWebtoon() {
        //given
        val id = 10L

        //when
        val result = webtoonReadService.getWebtoon(id)

        //then
        result.id shouldBe id
    }

    @DisplayName("일별 웹툰 불러오기")
    @Test
    fun getWebtoons() {
        //given
        val day = DayOfWeek.SATURDAY

        //when
        val result = webtoonReadService.getWebtoons(day)

        //then
        result.size shouldBe 2
        result.forEach {
            it.creators.size shouldNotBe 0
        }
    }

    @DisplayName("전체 웹툰 불러오기")
    @Test
    fun getEveryWebtoons() {
        //given - when
        val result = webtoonReadService.getEveryWebtoons()

        //then
        result.size shouldBe 12
        result.forEach {
            it.creators.size shouldNotBe 0
        }
    }

    @DisplayName("랜덤 웹툰 3개 불러오기")
    @Test
    fun getRandomThreeWebtoons() {
        //given
        val day = DayOfWeek.WEDNESDAY

        //when
        val result = webtoonReadService.getRandomThreeWebtoons(day)

        //then
        result.size shouldBe 3
    }
}
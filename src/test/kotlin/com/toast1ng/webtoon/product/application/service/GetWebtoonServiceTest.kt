package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.domain.DayOfWeek
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetWebtoonServiceTest @Autowired constructor(
    private val getWebtoonService: GetWebtoonService,
) {
    @DisplayName("랜덤 웹툰 3개 불러오기")
    @Test
    fun getRandomThreeWebtoons() {
        //given
        val day = DayOfWeek.WEDNESDAY

        //when
        val result = getWebtoonService.getRandomThreeWebtoons(day)

        //then
        result.size shouldBe 3
    }
}
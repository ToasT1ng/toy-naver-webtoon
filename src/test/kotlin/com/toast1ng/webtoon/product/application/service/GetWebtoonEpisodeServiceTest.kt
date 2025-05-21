package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@SpringBootTest
class GetWebtoonEpisodeServiceTest @Autowired constructor(
    private val getWebtoonEpisodeUseCase: GetWebtoonEpisodeUseCase,
) {

    @DisplayName("웹툰 회차 목록 불러오기")
    @Test
    fun getWebtoon() {
        //given
        val givenWebtoonId = 1L
        val command = GetPagingWebtoonEpisodesCommand(
            webtoonId = givenWebtoonId,
            pageNo = 0,
            pageSize = 10
        )

        //when
        val result = getWebtoonEpisodeUseCase.getWebtoonEpisodes(command)

        //then
        result.content.size shouldBe 2
        result.number shouldBe 0
        result.numberOfElements shouldBe 2
        result.isLast shouldBe true
        result.totalPages shouldBe 1
        result.totalElements shouldBe 2
    }
}
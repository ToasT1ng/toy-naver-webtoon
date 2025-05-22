package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeCommand
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@SpringBootTest
class LiveWebtoonEpisodeReadServiceTest @Autowired constructor(
    private val getLiveWebtoonEpisodeService: GetWebtoonEpisodeUseCase,
) {

    @DisplayName("웹툰 회차 1개 불러오기")
    @Test
    fun getWebtoonEpisode() {
        //given
        val givenWebtoonId = 1L
        val givenEpisodeId = 1L
        val command = GetWebtoonEpisodeCommand(
            id = givenEpisodeId,
            webtoonId = givenWebtoonId,
        )

        //when
        val result = getLiveWebtoonEpisodeService.getWebtoonEpisode(command)

        //then
        result.images.size shouldBe 8
        result.webtoon.id shouldBe givenWebtoonId
    }

    @DisplayName("웹툰 회차 목록 불러오기")
    @Test
    fun getWebtoonEpisodes() {
        //given
        val givenWebtoonId = 1L
        val command = GetPagingWebtoonEpisodesCommand(
            webtoonId = givenWebtoonId,
            pageNo = 1,
            pageSize = 10
        )

        //when
        val result = getLiveWebtoonEpisodeService.getWebtoonEpisodes(command)

        //then
        result.content.size shouldBe 1
        result.number shouldBe 1
        result.numberOfElements shouldBe 1
        result.isLast shouldBe true
        result.totalPages shouldBe 2
        result.totalElements shouldBe 11
    }
}
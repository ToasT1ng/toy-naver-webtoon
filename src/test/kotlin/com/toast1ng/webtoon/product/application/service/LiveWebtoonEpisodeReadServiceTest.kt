package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeCommand
import com.toast1ng.webtoon.test.utils.mockLocalDateTimeTo
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class LiveWebtoonEpisodeReadServiceTest @Autowired constructor(
    private val getLiveWebtoonEpisodeService: GetWebtoonEpisodeUseCase,
) {

    @BeforeEach
    fun beforeEach() {
        mockkStatic(LocalDateTime::class)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

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
        mockLocalDateTimeTo(LocalDateTime.of(2025, 5, 26, 12, 0))
        val givenWebtoonId = 1L
        val command = GetPagingWebtoonEpisodesCommand(
            webtoonId = givenWebtoonId,
            pageNo = 0,
            pageSize = 10
        )

        //when
        val result = getLiveWebtoonEpisodeService.getWebtoonEpisodes(command)

        //then
        result.content.size shouldBe 10
        result.content[0].id shouldBe 11
        result.number shouldBe 0
        result.numberOfElements shouldBe 10
        result.isLast shouldBe false
        result.totalPages shouldBe 2
        result.totalElements shouldBe 11
    }
}
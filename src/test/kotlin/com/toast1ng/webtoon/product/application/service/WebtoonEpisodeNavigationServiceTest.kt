package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeNavigationCommand
import com.toast1ng.webtoon.test.utils.mockLocalDateTimeTo
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class WebtoonEpisodeNavigationServiceTest @Autowired constructor(
    private val webtoonEpisodeNavigationService: WebtoonEpisodeNavigationService,
) {
    @BeforeEach
    fun beforeEach() {
        mockkStatic(LocalDateTime::class)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @DisplayName("웹툰 회차 네비게이션 불러오기")
    @ParameterizedTest(name = "{index}번 째 테스트 - 입력값: {0}")
    @ValueSource(longs = [1L, 2L, 3L, 4L, 5L])
    fun getWebtoonEpisodeNavigation(episodeId: Long) {
        //given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 4, 9, 12, 0))
        val command = GetWebtoonEpisodeNavigationCommand(
            webtoonId = 1L,
            episodeId = episodeId,
        )

        //when
        val result = shouldNotThrowAny {
            webtoonEpisodeNavigationService.getWebtoonEpisodeNavigation(command)
        }

        //then
        result.previousEpisodeId shouldBe makeExpectedPreviousEpisodeId(episodeId)
        result.currentEpisodeId shouldBe episodeId
        result.nextEpisodeId shouldBe makeExpectedNextEpisodeId(episodeId)
    }

    private fun makeExpectedPreviousEpisodeId(episodeId: Long): Long? {
        return if (episodeId > 1) episodeId - 1 else null
    }

    private fun makeExpectedNextEpisodeId(episodeId: Long): Long? {
        return if (episodeId < 5) episodeId + 1 else null
    }
}
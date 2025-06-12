package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import com.toast1ng.webtoon.test.utils.mockLocalDateTimeTo
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@MySpringBootTest
class ReadWebtoonEpisodeNavigationControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
){
    @BeforeEach
    fun beforeEach() {
        mockkStatic(LocalDateTime::class)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @DisplayName("웹툰 회차 네비게이션 조회 - 첫번째 회차")
    @Test
    fun getWebtoonEpisodeNavigation_firstEpisode() {
        // given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 5, 7, 12, 0))
        val webtoonId = 1L
        val episodeId = 1L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId/navigation") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.previousEpisodeId") { value(0L) }
            jsonPath("$.data.currentEpisodeId") { isNumber() }
            jsonPath("$.data.nextEpisodeId") { isNumber() }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 네비게이션 조회 - 중간 회차")
    @Test
    fun getWebtoonEpisodeNavigation_middleEpisode() {
        // given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 4, 1, 0, 0, 0))
        val webtoonId = 1L
        val episodeId = 3L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId/navigation") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.previousEpisodeId") { not(equalTo(0L)) }
            jsonPath("$.data.previousEpisodeId") { isNumber() }
            jsonPath("$.data.currentEpisodeId") { value(episodeId) }
            jsonPath("$.data.nextEpisodeId") { not(equalTo(0L)) }
            jsonPath("$.data.nextEpisodeId") { isNumber() }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 네비게이션 조회 - 마지막 회차")
    @Test
    fun getWebtoonEpisodeNavigation_lastEpisode() {
        // given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 6, 3, 0, 0, 0))
        val webtoonId = 1L
        val episodeId = 13L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId/navigation") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.previousEpisodeId") { isNumber() }
            jsonPath("$.data.currentEpisodeId") { isNumber() }
            jsonPath("$.data.nextEpisodeId") { value(0L) }
        }.andDo {
            print()
        }
    }
}
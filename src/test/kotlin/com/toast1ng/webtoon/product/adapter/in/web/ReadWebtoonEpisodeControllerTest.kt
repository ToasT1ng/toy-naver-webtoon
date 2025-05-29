package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.SortDirection
import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodesRequest
import com.toast1ng.webtoon.test.utils.mockLocalDateTimeTo
import com.toast1ng.webtoon.test.utils.toQueryString
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class ReadWebtoonEpisodeControllerTest @Autowired constructor(
    val mockMvc: MockMvc
){
    @BeforeEach
    fun beforeEach() {
        mockkStatic(LocalDateTime::class)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @DisplayName("웹툰 회차 목록 불러오기 - 정렬 기준: 최신순")
    @Test
    fun getWebtoonEpisodes_DESC() {
        // given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 5, 28, 12, 0))

        val request = PagingWebtoonEpisodesRequest(
            pageNo = 1,
            pageSize = 10,
            sortDirection = SortDirection.DESC,
        )
        val givenWebtoonId = 1L

        // when & then
        mockMvc.get("/webtoons/${givenWebtoonId}/episodes?${request.toQueryString()}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.webtoonId") { value(givenWebtoonId) }
            jsonPath("$.pageNo") { value(request.pageNo) }
            jsonPath("$.pageSize") { value(request.pageSize) }
            jsonPath("$.totalCount") { value(12) }
            jsonPath("$.isLastPage") { value(false) }
            jsonPath("$.content") { isArray() }
            jsonPath("$.content[0].episodeId") { value(12) }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 목록 불러오기 - 정렬 기준: 1화부터")
    @Test
    fun getWebtoonEpisodes_ASC() {
        // given
        mockLocalDateTimeTo(LocalDateTime.of(2025, 5, 7, 12, 0))

        val request = PagingWebtoonEpisodesRequest(
            pageNo = 2,
            pageSize = 5,
            sortDirection = SortDirection.ASC,
        )
        val givenWebtoonId = 1L

        // when & then
        mockMvc.get("/webtoons/${givenWebtoonId}/episodes?${request.toQueryString()}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.webtoonId") { value(givenWebtoonId) }
            jsonPath("$.pageNo") { value(request.pageNo) }
            jsonPath("$.pageSize") { value(request.pageSize) }
            jsonPath("$.totalCount") { value(9) }
            jsonPath("$.isLastPage") { value(true) }
            jsonPath("$.content") { isArray() }
            jsonPath("$.content[0].episodeId") { value(6) }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 단건 조회")
    @Test
    fun getWebtoonEpisode() {
        // given
        val webtoonId = 1L
        val episodeId = 1L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.episodeId") { value(episodeId) }
            jsonPath("$.title") { exists() }
            jsonPath("$.thumbnail") { exists() }
            jsonPath("$.uploadDate") { exists() }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 단건 조회 - 회차가 없는 경우")
    @Test
    @Disabled
    //TODO 성공하도록 수정
    fun getWebtoonEpisode_error() {
        // given
        val webtoonId = 1L
        val episodeId = 1L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.episodeId") { value(episodeId) }
            jsonPath("$.title") { exists() }
            jsonPath("$.thumbnail") { exists() }
            jsonPath("$.uploadDate") { exists() }
        }.andDo {
            print()
        }
    }

    //TODO Controller 및 Test Code 분리
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
            jsonPath("$.previousEpisodeId") { value(0L) }
            jsonPath("$.currentEpisodeId") { isNumber() }
            jsonPath("$.nextEpisodeId") { isNumber() }
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
            jsonPath("$.previousEpisodeId") { not(equalTo(0L)) }
            jsonPath("$.previousEpisodeId") { isNumber() }
            jsonPath("$.currentEpisodeId") { value(episodeId) }
            jsonPath("$.nextEpisodeId") { not(equalTo(0L)) }
            jsonPath("$.nextEpisodeId") { isNumber() }
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
            jsonPath("$.previousEpisodeId") { isNumber() }
            jsonPath("$.currentEpisodeId") { isNumber() }
            jsonPath("$.nextEpisodeId") { value(0L) }
        }.andDo {
            print()
        }
    }

}

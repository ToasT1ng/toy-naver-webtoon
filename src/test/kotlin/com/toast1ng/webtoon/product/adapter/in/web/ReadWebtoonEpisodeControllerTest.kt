package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.SortDirection
import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodesRequest
import com.toast1ng.webtoon.test.utils.mockLocalDateTimeTo
import com.toast1ng.webtoon.test.utils.toQueryString
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
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
            jsonPath("$.data.webtoonId") { value(givenWebtoonId) }
            jsonPath("$.data.pageNo") { value(request.pageNo) }
            jsonPath("$.data.pageSize") { value(request.pageSize) }
            jsonPath("$.data.totalCount") { value(12) }
            jsonPath("$.data.isLastPage") { value(false) }
            jsonPath("$.data.content") { isArray() }
            jsonPath("$.data.content[0].episodeId") { value(12) }
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
            jsonPath("$.data.webtoonId") { value(givenWebtoonId) }
            jsonPath("$.data.pageNo") { value(request.pageNo) }
            jsonPath("$.data.pageSize") { value(request.pageSize) }
            jsonPath("$.data.totalCount") { value(9) }
            jsonPath("$.data.isLastPage") { value(true) }
            jsonPath("$.data.content") { isArray() }
            jsonPath("$.data.content[0].episodeId") { value(6) }
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
            jsonPath("$.data.episodeId") { value(episodeId) }
            jsonPath("$.data.title") { exists() }
            jsonPath("$.data.thumbnail") { exists() }
            jsonPath("$.data.uploadDate") { exists() }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 단건 조회 - 회차가 없는 경우")
    @Test
    fun getWebtoonEpisode_error() {
        // given
        val webtoonId = 1L
        val episodeId = 100L

        // when & then
        mockMvc.get("/webtoons/$webtoonId/episodes/$episodeId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isInternalServerError() }
            jsonPath("$.code") { value(50000) }
            jsonPath("$.message") { value("서버 내부 오류가 발생했습니다.") }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }



}

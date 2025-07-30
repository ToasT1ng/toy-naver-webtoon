package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodeCommentRequest
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import com.toast1ng.webtoon.test.utils.toQueryString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@MySpringBootTest
class ReadWebtoonEpisodeCommentControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
){
    @DisplayName("웹툰 회차 댓글 불러오기")
    @Test
    fun getWebtoonEpisodeComments() {
        //given
        val givenWebtoonId = 1L
        val givenEpisodeId = 2L
        val request = PagingWebtoonEpisodeCommentRequest(
            pageNo = 1,
            pageSize = 10,
        )

        // when & then
        mockMvc.get("/webtoons/${givenWebtoonId}/episodes/${givenEpisodeId}/comments?${request.toQueryString()}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.content") { isArray() }
            jsonPath("$.data.content.size()") { value(2) }
            jsonPath("$.data.content[0].id") { value(5) }
            jsonPath("$.data.content[0].parentId") { value(0) }
            jsonPath("$.data.content[0].episodeId") { value(2) }
            jsonPath("$.data.content[0].user") { exists() }
            jsonPath("$.data.content[0].user.id") { value(1) }
            jsonPath("$.data.content[0].user.username") { value("admin") }
            jsonPath("$.data.content[0].content") { exists() }

            jsonPath("$.data.content[1].id") { value(6) }
            jsonPath("$.data.content[1].parentId") { value(0) }
            jsonPath("$.data.content[1].episodeId") { value(2) }
            jsonPath("$.data.content[1].user") { exists() }
            jsonPath("$.data.content[1].user.id") { value(2) }
            jsonPath("$.data.content[1].user.username") { value("user1") }
            jsonPath("$.data.content[1].content") { exists() }
        }.andDo {
            print()
        }
    }

    @DisplayName("웹툰 회차 대댓글 불러오기")
    @Test
    fun getChildCommentsByParentId() {
        //given
        val givenWebtoonId = 1L
        val givenEpisodeId = 1L
        val givenCommentId = 1L
        val request = PagingWebtoonEpisodeCommentRequest(
            pageNo = 1,
            pageSize = 10,
        )

        // when & then
        mockMvc.get("/webtoons/${givenWebtoonId}/episodes/${givenEpisodeId}/comments/${givenCommentId}?${request.toQueryString()}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.content") { isArray() }
            jsonPath("$.data.content.size()") { value(2) }
            jsonPath("$.data.content[0].id") { value(3) }
            jsonPath("$.data.content[0].parentId") { value(1) }
            jsonPath("$.data.content[0].episodeId") { value(1) }
            jsonPath("$.data.content[0].user") { exists() }
            jsonPath("$.data.content[0].user.id") { value(2) }
            jsonPath("$.data.content[0].user.username") { value("user1") }
            jsonPath("$.data.content[0].content") { exists() }

            jsonPath("$.data.content[1].id") { value(4) }
            jsonPath("$.data.content[1].parentId") { value(1) }
            jsonPath("$.data.content[1].episodeId") { value(1) }
            jsonPath("$.data.content[1].user") { exists() }
            jsonPath("$.data.content[1].user.id") { value(1) }
            jsonPath("$.data.content[1].user.username") { value("admin") }
            jsonPath("$.data.content[1].content") { exists() }
        }.andDo {
            print()
        }
    }
}
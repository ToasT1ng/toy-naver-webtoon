package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@MySpringBootTest
class ReadWebtoonProductControllerTest @Autowired constructor(
    val mockMvc: MockMvc
){

    @DisplayName("일별 추천 웹툰 3개 불러오기")
    @Test
    fun getDailyRecommendedWebtoon() {
        val dayOfWeekValue = DayOfWeek.MONDAY.value

        mockMvc.get("/webtoons/daily/recommend/three?dayOfWeek=$dayOfWeekValue") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.result") { exists() }
        }.andDo { print() }
    }

    @DisplayName("일별 웹툰 불러오기")
    @Test
    fun getDailyWebtoons() {
        // given
        val dayOfWeekValue = DayOfWeek.SATURDAY.value

        // when & then
        mockMvc.get("/webtoons/daily?dayOfWeek=$dayOfWeekValue") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.result") { exists() }
        }.andDo {
            print()
        }
    }
    @DisplayName("월별 추천 웹툰 3개 불러오기")
    @Test
    fun getMonthlyRecommendedWebtoon() {
        mockMvc.get("/webtoons/monthly/recommend/three") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.result") { exists() }
        }.andDo { print() }
    }

    @DisplayName("전체 웹툰 목록 불러오기")
    @Test
    fun getWebtoons() {
        mockMvc.get("/webtoons") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.result") { exists() }
        }.andDo { print() }
    }

    @DisplayName("웹툰 단건 조회")
    @Test
    fun getWebtoon() {
        val webtoonId = 1L

        mockMvc.get("/webtoons/$webtoonId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.data.id") { exists() }
        }.andDo { print() }
    }
}
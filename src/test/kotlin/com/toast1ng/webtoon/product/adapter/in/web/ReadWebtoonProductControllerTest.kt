package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.domain.DayOfWeek
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

//TODO SpringBooTest Context 1개 사용되도록 수정
@SpringBootTest
@AutoConfigureMockMvc
class ReadWebtoonProductControllerTest @Autowired constructor(
    val mockMvc: MockMvc
){
    //TODO 테스트 정확히 작성
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
}
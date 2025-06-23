package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.domain.auth.JwtErrorResponseCode
import com.toast1ng.webtoon.common.response.CommonErrorResponseCode
import com.toast1ng.webtoon.common.response.CommonSuccessResponseCode
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@MySpringBootTest
class RefreshTokenControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
){

    @DisplayName("토큰 Refresh 성공")
    @Test
    fun refreshAccessToken() {
        //given
        val givenRefreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwidHlwZSI6InJlZnJlc2giLCJ0b2tlbklkIjoiOWZiNzk1ZTAtN2E2OC00NzBlLTk5ZDAtMzE3YjhiOTAzNmI0IiwiaWF0IjoxNzUwNjgwNjM2LCJleHAiOjE3NTA2OTE0MzZ9.wiViOy3-4UkSROQhLEuU_6utK1vazxeBe7k9aiFGJww"
        val expectedResponseCode = CommonSuccessResponseCode.OK

        //when - then
        mockMvc.post("/auth/refresh") {
            contentType = MediaType.APPLICATION_JSON
            header(
                "Authorization",
                "Bearer ${givenRefreshToken}"
            )
        }.andExpect {
            status().isOk
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { isMap() }
            jsonPath("$.data.accessToken") { exists() }
        }.andDo {
            print()
        }
    }

    @DisplayName("토큰 Refresh 실패 - 빈 토큰")
    @Test
    fun refreshAccessTokenFailBecauseOfEmptyRefreshToken() {
        //given
        val expectedResponseCode = CommonErrorResponseCode.BAD_REQUEST

        //when - then
        mockMvc.post("/auth/refresh") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("토큰 Refresh 실패 - 잘못된 토큰")
    @Test
    fun refreshAccessTokenFailBecauseOfWrongRefreshToken() {
        //given
        val givenRefreshToken = "1234"
        val expectedResponseCode = JwtErrorResponseCode.JWT_TOKEN_MALFORMED

        //when - then
        mockMvc.post("/auth/refresh") {
            contentType = MediaType.APPLICATION_JSON
            header(
                "Authorization",
                "Bearer ${givenRefreshToken}"
            )
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

}
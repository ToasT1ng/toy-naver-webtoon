package com.toast1ng.webtoon.member.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.toast1ng.webtoon.common.config.JwtProvider
import com.toast1ng.webtoon.common.response.CommonErrorResponseCode
import com.toast1ng.webtoon.common.response.CommonSuccessResponseCode
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@MySpringBootTest
class MyPageControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtProvider: JwtProvider,
){
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("마이페이지 접근 성공")
    @Test
    fun approachToMyPageSuccess() {
        //given
        val expectedResponseCode = CommonSuccessResponseCode.OK
        val token = jwtProvider.createToken("user1")

        //when - then
        mockMvc.get("/mypage") {
            contentType = MediaType.APPLICATION_JSON
            headers {
                set("Authorization", "Bearer $token")
            }
        }.andExpect {
            status().isOk
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("마이페이지 접근 실패 - 인증되지 않은 사용자 (토큰 없음)")
    @Test
    fun approachToMyPageFailBecauseOfNotLogin() {
        //given
        val expectedResponseCode = CommonErrorResponseCode.UNAUTHORIZED

        //when - then
        mockMvc.get("/mypage") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status().isUnauthorized
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("마이페이지 접근 실패 - 인증되지 않은 사용자 (없는 사용자)")
    @Test
    fun approachToMyPageFailBecauseOfNotExitsUser() {
        //given
        val expectedResponseCode = CommonErrorResponseCode.UNAUTHORIZED
        val token = jwtProvider.createToken("testUser1")

        //when - then
        mockMvc.get("/mypage") {
            contentType = MediaType.APPLICATION_JSON
            headers {
                set("Authorization", "Bearer $token")
            }
        }.andExpect {
            status().isUnauthorized
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }
}
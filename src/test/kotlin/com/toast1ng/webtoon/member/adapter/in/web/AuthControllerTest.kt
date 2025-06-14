package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import com.fasterxml.jackson.databind.ObjectMapper
import com.toast1ng.webtoon.common.response.CommonErrorResponseCode
import com.toast1ng.webtoon.common.response.CommonSuccessResponseCode
import com.toast1ng.webtoon.member.adapter.`in`.web.request.LoginRequest
import com.toast1ng.webtoon.member.adapter.`in`.web.request.SignupRequest
import com.toast1ng.webtoon.member.domain.error.UserAuthErrorResponseCode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@MySpringBootTest
class AuthControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
){
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("회원가입 성공")
    @Test
    fun signupSuccess() {
        //given
        val request = SignupRequest(
            username = "newuser123",
            password = "securepassword"
        )
        val expectedResponseCode = CommonSuccessResponseCode.OK

        //when - then
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isOk
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("회원가입 실패 - 빈 유저명")
    @Test
    fun signupFailBecauseOfEmptyUsername() {
        //given
        val request = SignupRequest(
            username = "",
            password = "securepassword"
        )
        val expectedResponseCode = CommonErrorResponseCode.BAD_REQUEST

        //when - then
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("회원가입 실패 - 빈 비밀번호")
    @Test
    fun signupFailBecauseOfEmptyPassword() {
        //given
        val request = SignupRequest(
            username = "newuser123",
            password = ""
        )
        val expectedResponseCode = CommonErrorResponseCode.BAD_REQUEST

        //when - then
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("회원가입 실패 - 이미 존재하는 유저명")
    @Test
    fun signupFailBecauseOfAlreadyExistsUser() {
        //given
        val request = SignupRequest(
            username = "user1",
            password = "securepassword"
        )
        val expectedResponseCode = UserAuthErrorResponseCode.USER_ALREADY_EXISTS

        //when - then
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("로그인 성공")
    @Test
    fun loginSuccess() {
        //given
        val request = LoginRequest(
            username = "user1",
            password = "user1password"
        )
        val expectedResponseCode = CommonSuccessResponseCode.OK

        //when - then
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isOk
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("로그인 실패 - 빈 유저명")
    @Test
    fun loginFailBecauseOfEmptyUsername() {
        //given
        val request = LoginRequest(
            username = "",
            password = "user1password"
        )
        val expectedResponseCode = CommonErrorResponseCode.BAD_REQUEST

        //when - then
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("로그인 실패 - 빈 비밀번호")
    @Test
    fun loginFailBecauseOfEmptyPassword() {
        //given
        val request = LoginRequest(
            username = "user1",
            password = ""
        )
        val expectedResponseCode = CommonErrorResponseCode.BAD_REQUEST

        //when - then
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("로그인 실패 - 존재하지 않는 유저")
    @Test
    fun loginFailBecauseOfUserNotExists() {
        //given
        val request = LoginRequest(
            username = "some_non_existent_user",
            password = "user1password"
        )
        val expectedResponseCode = UserAuthErrorResponseCode.USER_NOT_FOUND

        //when - then
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isNotFound
            jsonPath("$.code") { value(expectedResponseCode.code) }
            jsonPath("$.message") { value(expectedResponseCode.message) }
            jsonPath("$.data") { doesNotExist() }
        }.andDo {
            print()
        }
    }

    @DisplayName("로그인 실패 - 비밀번호 틀림")
    @Test
    fun loginFail() {
        //given
        val request = LoginRequest(
            username = "user1",
            password = "wrongpassword"
        )
        val expectedResponseCode = UserAuthErrorResponseCode.INVALID_PASSWORD


        //when - then
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
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
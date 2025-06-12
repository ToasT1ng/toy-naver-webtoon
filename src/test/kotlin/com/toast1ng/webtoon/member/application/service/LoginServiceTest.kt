package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.command.LoginCommand
import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserRole
import com.toast1ng.webtoon.member.domain.error.UserAuthErrorResponseCode
import com.toast1ng.webtoon.member.domain.error.UserAuthException
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.reset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@MySpringBootTest
class LoginServiceTest @Autowired constructor(
    private val loginService: LoginService,
    private val readUserPort: ReadUserPort,
    private val passwordEncoder: PasswordEncoder
) {
    @Nested
    @DisplayName("실제 데이터베이스 사용")
    inner class RealData {
        @DisplayName("로그인 성공")
        @Test
        fun loginSuccess() {
            // given
            val command = LoginCommand(username = "user1", password = "user1password")

            // when & then
            shouldNotThrowAny {
                loginService.login(command)
            }
        }

    }

    @Nested
    @DisplayName("Mocking된 사용자 정보 사용")
    inner class MockingData {
        private val givenUsername = "not_exists_user"
        private val correctPassword = "test_user_password"

        @AfterEach
        fun afterEach() {
            reset(readUserPort)
        }

        @DisplayName("로그인 성공")
        @Test
        fun loginSuccess() {
            // given
            mockingReadUserPort()
            val command = LoginCommand(username = givenUsername, password = correctPassword)

            // when & then
            shouldNotThrowAny {
                loginService.login(command)
            }
        }

        @DisplayName("로그인 실패 - 유저 존재하지 않음")
        @Test
        fun loginFailBecauseOfUserIsNotExists() {
            // given
            mockingReadUserPortReturnNull()
            val command = LoginCommand(username = givenUsername, password = correctPassword)

            // when
            val exception = shouldThrow<UserAuthException> {
                loginService.login(command)
            }

            // then
            exception.code shouldBe UserAuthErrorResponseCode.USER_NOT_FOUND
        }

        @DisplayName("로그인 실패 - 비밀번호 불일치")
        @Test
        fun loginFailBecauseOfWrongPassword() {
            // given
            mockingReadUserPort()
            val command = LoginCommand(username = givenUsername, password = "wrong_password")

            // when
            val exception = shouldThrow<UserAuthException> {
                loginService.login(command)
            }

            // then
            exception.code shouldBe UserAuthErrorResponseCode.INVALID_PASSWORD
        }

        private fun mockingReadUserPortReturnNull() {
            doReturn(null)
                .`when`(readUserPort)
                .getUser(
                    any()
                )
        }

        private fun mockingReadUserPort() {
            doReturn(
                User(
                    username = givenUsername,
                    password = passwordEncoder.encode(correctPassword),
                    role = UserRole.ADMIN
                )
            )
                .`when`(readUserPort)
                .getUser(
                    any()
                )
        }
    }
}


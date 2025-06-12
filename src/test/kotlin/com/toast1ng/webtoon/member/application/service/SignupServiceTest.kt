package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.command.SignupCommand
import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.application.port.out.RegisterUserPort
import com.toast1ng.webtoon.member.domain.UserFactory
import com.toast1ng.webtoon.member.domain.UserRole
import com.toast1ng.webtoon.member.domain.error.UserAuthErrorResponseCode
import com.toast1ng.webtoon.member.domain.error.UserAuthException
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.reset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@MySpringBootTest
class SignupServiceTest @Autowired constructor(
    private val signupService: SignupService,
    private val readUserPort: ReadUserPort,
    private val registerUserPort: RegisterUserPort,
    private val passwordEncoder: PasswordEncoder
) {

    @Nested
    @DisplayName("실제 데이터베이스 사용")
    inner class RealData {
        @DisplayName("회원가입 성공")
        @Test
        fun signupSuccess() {
            // given
            val givenUsername = "test_user"
            val givenPassword = "test_user_password"
            val givenRole = UserRole.USER
            val command = SignupCommand(
                username = givenUsername,
                password = givenPassword,
                role = givenRole
            )

            // when
            val user = shouldNotThrowAny { signupService.signup(command) }

            // then
            user.username shouldBe givenUsername
            passwordEncoder.matches(givenPassword, user.password) shouldBe true
            user.role shouldBe givenRole
        }
    }

    @Nested
    @DisplayName("Mocking된 사용자 정보 사용")
    inner class MockingData {
        private val givenUsername = "new_user"
        private val givenPassword = "test_user_password"

        @BeforeEach
        fun setup() {
            doReturn(
                UserFactory.create(
                    username = givenUsername,
                    password = passwordEncoder.encode(givenPassword),
                    role = UserRole.USER
                )
            )
                .`when`(registerUserPort)
                .registerUser(any())
        }

        @AfterEach
        fun afterEach() {
            reset(readUserPort)
            reset(registerUserPort)
        }

        @DisplayName("회원가입 성공")
        @Test
        fun signupSuccess() {
            // given
            mockingReadUserPortIsUserExists()
            val command = SignupCommand(
                username = givenUsername,
                password = givenPassword,
                role = UserRole.USER
            )

            // when & then
            val user = shouldNotThrowAny { signupService.signup(command) }

            // then
            user.username shouldBe givenUsername
        }

        @DisplayName("회원가입 실패 - 유저가 이미 존재함")
        @Test
        fun signupFailBecauseOfUsernameAlreadyExists() {
            // given
            mockingReadUserPortIsUserExists(true)
            val command = SignupCommand(
                username = givenUsername,
                password = givenPassword,
                role = UserRole.USER
            )

            // when & then
            val exception = shouldThrow<UserAuthException> { signupService.signup(command) }

            // then
            exception.code shouldBe UserAuthErrorResponseCode.USER_ALREADY_EXISTS
        }

        private fun mockingReadUserPortIsUserExists(returnValue: Boolean = false) {
            doReturn(returnValue)
                .`when`(readUserPort)
                .isUserExists(any())
        }
    }
}
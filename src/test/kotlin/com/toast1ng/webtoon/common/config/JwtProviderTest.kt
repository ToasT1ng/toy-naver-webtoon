package com.toast1ng.webtoon.common.config

import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@MySpringBootTest
class JwtProviderTest @Autowired constructor(
    val jwtProvider: JwtProvider
) {
    @DisplayName("JWT 토큰 생성 및 검증 테스트")
    @Test
    fun makeTokenAndValidate() {
        // Given
        val username = "user1"

        // When
        val token = jwtProvider.createToken(username)
        println("Generated Token: $token")

        // Then
        jwtProvider.isValid(token) shouldBe true
        jwtProvider.getUsername(token) shouldBe username
    }
}
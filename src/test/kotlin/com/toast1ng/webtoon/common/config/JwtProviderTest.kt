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
    @DisplayName("JWT Access 토큰 생성 및 검증 테스트")
    @Test
    fun makeAccessTokenAndValidate() {
        // Given
        val id = 1L

        // When
        val token = jwtProvider.createAccessToken(id)
        println("Generated Access Token: $token")

        // Then
        jwtProvider.isValid(token) shouldBe true
        jwtProvider.getUserId(token) shouldBe id
    }

    @DisplayName("JWT Refresh 토큰 생성 및 검증 테스트")
    @Test
    fun makeRefreshTokenAndValidate() {
        // Given
        val id = 1L

        // When
        val token = jwtProvider.createRefreshToken(id, false)
        println("Generated Refresh Token: $token")

        // Then
        jwtProvider.isValid(token) shouldBe true
        jwtProvider.getUserId(token) shouldBe id
    }
}
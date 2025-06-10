package com.toast1ng.webtoon.common.config

import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@MySpringBootTest
class PasswordEncoderTest @Autowired constructor(
    private val passwordEncoder: PasswordEncoder
) {
    @Disabled("필요할 때 사용")
    @DisplayName("비밀번호 생성")
    @Test
    fun passwordEncoder() {
        //given
        val password = "user1password"

        //when
        val encodedPassword = passwordEncoder.encode(password)

        //then
        println("Encoded Password ==> $encodedPassword")

    }
}
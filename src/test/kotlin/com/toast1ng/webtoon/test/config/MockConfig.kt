package com.toast1ng.webtoon.test.config

import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.application.port.out.RegisterUserPort
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.SpyBean

@TestConfiguration
class MockConfig {
    @SpyBean
    lateinit var readUserPort: ReadUserPort

    @SpyBean
    lateinit var registerUserPort: RegisterUserPort
}
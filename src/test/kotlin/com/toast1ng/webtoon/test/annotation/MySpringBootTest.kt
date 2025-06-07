package com.toast1ng.webtoon.test.annotation

import com.toast1ng.webtoon.test.config.MockConfig
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureMockMvc
@SpringBootTest(classes = [MockConfig::class])
annotation class MySpringBootTest {
}
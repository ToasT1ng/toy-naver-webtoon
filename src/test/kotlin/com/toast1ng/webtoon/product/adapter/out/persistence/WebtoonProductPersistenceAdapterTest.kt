package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.application.port.out.GetWebtoonProductQuery
import com.toast1ng.webtoon.product.domain.DayOfWeek
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class WebtoonProductPersistenceAdapterTest @Autowired constructor(
    private val webtoonProductPersistenceAdapter: WebtoonProductPersistenceAdapter,
) {

    @Test
    @DisplayName("웹툰 전체 리스트 DB상에서 불러오기")
    fun getEveryWebtoons() {
        //given
        val query = GetWebtoonProductQuery()

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoons(query)

        //then
        result.size shouldBe 12
    }

    @Test
    @DisplayName("웹툰 리스트 DB상에서 불러오기")
    fun getWebtoons() {
        //given
        val query = GetWebtoonProductQuery(
            day = DayOfWeek.WEDNESDAY,
        )

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoons(query)

        //then
        result.size shouldBe 4
    }

    @Test
    @DisplayName("웹툰 하나 DB상에서 불러오기")
    fun getWebtoon() {
        //given
        val query = GetWebtoonProductQuery(
            id = 1L,
        )

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoon(query)

        //then
        result?.id shouldBe 1L
        result?.title shouldBe "슬기로운 코딩 생활"
    }
}
package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.QuerySortOption
import com.toast1ng.webtoon.common.domain.SortDirection
import com.toast1ng.webtoon.product.application.port.out.query.ThreeWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortColumn
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortQuery
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
@MySpringBootTest
class WebtoonProductPersistenceAdapterTest @Autowired constructor(
    private val webtoonProductPersistenceAdapter: WebtoonProductPersistenceAdapter,
) {

    @Test
    @DisplayName("웹툰 전체 리스트 DB상에서 불러오기")
    fun getEveryWebtoons() {
        //given
        val query = WebtoonProductQuery()

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoons(query)

        //then
        result.size shouldBe 12
    }

    @Test
    @DisplayName("웹툰 리스트 DB상에서 불러오기")
    fun getWebtoons() {
        //given
        val query = WebtoonProductQuery(
            day = DayOfWeek.WEDNESDAY,
        )

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoons(query)

        //then
        result.size shouldBe 4
    }

    @Test
    @DisplayName("정렬된 웹툰 리스트 DB상에서 불러오기 - 마지막 업로드 기준")
    fun getWebtoonsSortedByLatestEpisodeUpload() {
        // given
        val query = WebtoonProductSortQuery(
            day = DayOfWeek.TUESDAY,
            sortOptions = listOf(
                QuerySortOption(
                    key = WebtoonProductSortColumn.UPLOAD_AT,
                    direction = SortDirection.ASC
                ),
            )
        )

        // when
        val result = webtoonProductPersistenceAdapter.getWebtoonsByLatestEpisodeUpload(query)

        // then
        result.size shouldBe 2
        result[0].id shouldBe 4L
        result[1].id shouldBe 1L
    }

    @Test
    @DisplayName("정렬된 웹툰 리스트 DB상에서 불러오기 - 평점 기준")
    fun getSortedWebtoons_RATING() {
        // given
        val query = WebtoonProductSortQuery(
            day = DayOfWeek.WEDNESDAY,
            sortOptions = listOf(
                QuerySortOption(
                    key = WebtoonProductSortColumn.RATING,
                    direction = SortDirection.DESC,
                )
            )
        )

        // when
        val result = webtoonProductPersistenceAdapter.getSortedWebtoons(query)

        // then
        result.size shouldBe 4
        result.zipWithNext().forEach { (prev, next) ->
            prev.rating shouldBeGreaterThan next.rating
        }
    }

    @Test
    @DisplayName("정렬된 웹툰 리스트 DB상에서 불러오기 - 조회수 기준")
    fun getSortedWebtoons_VIEWS() {
        // given
        val query = WebtoonProductSortQuery(
            day = DayOfWeek.WEDNESDAY,
            sortOptions = listOf(
                QuerySortOption(
                    key = WebtoonProductSortColumn.VIEWS,
                    direction = SortDirection.DESC,
                )
            )
        )

        // when
        val result = webtoonProductPersistenceAdapter.getSortedWebtoons(query)

        // then
        result.size shouldBe 4
        result.zipWithNext().forEach { (prev, next) ->
            prev.views shouldBeGreaterThan next.views
        }
    }

    @Test
    @DisplayName("웹툰 하나 DB상에서 불러오기")
    fun getWebtoon() {
        //given
        val query = WebtoonProductQuery(
            id = 1L,
        )

        //when
        val result = webtoonProductPersistenceAdapter.getWebtoon(query)

        //then
        result?.id shouldBe 1L
        result?.title shouldBe "슬기로운 코딩 생활"
    }

    @Test
    @DisplayName("랜덤 웹툰 N개 불러오기")
    fun getRandomThreeWebtoon() {
        //given
        val query = ThreeWebtoonsQuery(
            day = DayOfWeek.SATURDAY,
            randomRowNumbers = listOf(2, 1),
        )

        //when
        val result = webtoonProductPersistenceAdapter.getRandomWebtoons(query)

        //then
        result.size shouldBe 2
        result[0].id shouldBe 8L
        result[1].id shouldBe 10L
    }
}
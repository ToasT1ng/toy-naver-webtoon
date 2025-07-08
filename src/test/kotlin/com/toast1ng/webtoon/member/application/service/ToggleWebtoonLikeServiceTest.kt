package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import com.toast1ng.webtoon.member.domain.UserRole
import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import com.toast1ng.webtoon.product.domain.WebtoonStatus
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class ToggleWebtoonLikeServiceTest {
    @Mock
    lateinit var toggleWebtoonLikePort: ToggleWebtoonLikePort

    @Mock
    lateinit var readWebtoonLikePort: ReadWebtoonLikePort

    @Mock
    lateinit var readWebtoonProductPort: ReadWebtoonProductPort

    @InjectMocks
    lateinit var service: ToggleWebtoonLikeService

    private val givenUser = User(
        id = 100L,
        username = "test_user",
        password = "some_password",
        role = UserRole.USER,
    )

    @DisplayName("좋아요가 없으면 새로 생성된다")
    @Test
    fun likeWebtoonFirstTime() {
        // given
        val givenWebtoonId = 1L
        mockingWebtoonProductPort(webtoonId = givenWebtoonId, webtoonProduct = makeWebtoonProduct(givenWebtoonId))
        mockingWebtoonLikePort(userId = givenUser.id, webtoonId = givenWebtoonId, webtoonLike = null)

        // when
        shouldNotThrowAny {
            service.likeWebtoon(user = givenUser, webtoonId = givenWebtoonId)
        }

        // then
        verify(toggleWebtoonLikePort, times(1)).toggleWebtoonLike(
            check {
                assert(it.id == 0L)
                assert(it.user.id == givenUser.id)
                assert(it.webtoon.id == givenWebtoonId)
                assert(it.status == WebtoonLikedStatus.LIKED)
            }
        )
    }

    @DisplayName("존재하지 않는 웹툰에 좋아요를 누르면 예외가 발생한다")
    @Test
    fun likeWebtoonWithNotExistsWebtoon() {
        // given
        val givenWebtoonId = 1L
        mockingWebtoonProductPort(webtoonId = givenWebtoonId, webtoonProduct = null)

        // when
        val exception = shouldThrowAny {
            service.likeWebtoon(user = givenUser, webtoonId = givenWebtoonId)
        }

        // then
        verify(toggleWebtoonLikePort, times(0)).toggleWebtoonLike(any())
        exception.javaClass shouldBe IllegalArgumentException::class.java
    }

    @DisplayName("이미 좋아요했던 적이 있으면 좋아요 상태를 변경한다")
    @Test
    fun likeWebtoonAlreadyLiked() {
        // given
        val givenWebtoonId = 1L
        val expectedWebtoonLikeId = 123L
        mockingWebtoonProductPort(webtoonId = givenWebtoonId, webtoonProduct = makeWebtoonProduct(givenWebtoonId))
        mockingWebtoonLikePort(
            userId = givenUser.id,
            webtoonId = givenWebtoonId,
            webtoonLike = makeWebtoonLike(id = expectedWebtoonLikeId, webtoonId = givenWebtoonId, status = WebtoonLikedStatus.UNLIKED)
        )

        // when
        shouldNotThrowAny {
            service.likeWebtoon(user = givenUser, webtoonId = givenWebtoonId)
        }

        // then
        verify(toggleWebtoonLikePort, times(1)).toggleWebtoonLike(
            check {
                assert(it.id == expectedWebtoonLikeId)
                assert(it.user.id == givenUser.id)
                assert(it.webtoon.id == givenWebtoonId)
                assert(it.status == WebtoonLikedStatus.LIKED)
            }
        )
    }

    @DisplayName("좋아요를 해제한다.")
    @Test
    fun unlikeWebtoon() {
        // given
        val givenWebtoonId = 1L
        val expectedWebtoonLikeId = 123L
        mockingWebtoonProductPort(webtoonId = givenWebtoonId, webtoonProduct = makeWebtoonProduct(givenWebtoonId))
        mockingWebtoonLikePort(
            userId = givenUser.id,
            webtoonId = givenWebtoonId,
            webtoonLike = makeWebtoonLike(id = expectedWebtoonLikeId, webtoonId = givenWebtoonId, status = WebtoonLikedStatus.LIKED)
        )

        // when
        shouldNotThrowAny {
            service.unlikeWebtoon(user = givenUser, webtoonId = givenWebtoonId)
        }

        // then
        verify(toggleWebtoonLikePort, times(1)).toggleWebtoonLike(
            check {
                assert(it.id == expectedWebtoonLikeId)
                assert(it.user.id == givenUser.id)
                assert(it.webtoon.id == givenWebtoonId)
                assert(it.status == WebtoonLikedStatus.UNLIKED)
            }
        )
    }

    @DisplayName("존재하지 않는 웹툰에 좋아요 해제를 누르면 예외가 발생한다")
    @Test
    fun unlikeWebtoonWithNotExistsWebtoon() {
        // given
        val givenWebtoonId = 1L
        mockingWebtoonProductPort(webtoonId = givenWebtoonId, webtoonProduct = null)

        // when
        val exception = shouldThrowAny {
            service.unlikeWebtoon(user = givenUser, webtoonId = givenWebtoonId)
        }

        // then
        verify(toggleWebtoonLikePort, times(0)).toggleWebtoonLike(any())
        exception.javaClass shouldBe IllegalArgumentException::class.java
    }

    private fun mockingWebtoonLikePort(userId: Long, webtoonId: Long, webtoonLike: UserLikedWebtoon? = null) {
        whenever(
            readWebtoonLikePort.getWebtoonLike(
                UserLikedWebtoonQuery(
                    userId = userId,
                    webtoonId = webtoonId
                )
            )
        ).thenReturn(webtoonLike)
    }

    private fun mockingWebtoonProductPort(webtoonId: Long, webtoonProduct: WebtoonProduct? = null) {
        whenever(readWebtoonProductPort.findWebtoonProductById(webtoonId))
            .thenReturn(webtoonProduct)
    }

    private fun makeWebtoonProduct(webtoonId: Long): WebtoonProduct {
        return WebtoonProduct(
            id = webtoonId,
            title = "Test Webtoon",
            description = "",
            views = 1,
            rating = BigDecimal("1.1"),
            likes = 1,
            mainThumbnail = "test.jpg",
            subThumbnail = "test.jpg",
            day = DayOfWeek.MONDAY,
            restrictedAge = 19,
            status = WebtoonStatus.serialize,
            genres = emptyList(),
            creators = emptyList(),
        )
    }

    private fun makeWebtoonLike(id: Long, webtoonId: Long, status: WebtoonLikedStatus): UserLikedWebtoon {
        return UserLikedWebtoon(
            id = id,
            user = givenUser,
            webtoon = makeWebtoonProduct(webtoonId),
            status = status,
            updatedAt = LocalDateTime.now()
        )
    }
}
package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.ToggleWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import com.toast1ng.webtoon.member.domain.UserLikedWebtoonFactory
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import org.springframework.stereotype.Service

@Service
class ToggleWebtoonLikeService(
    private val toggleWebtoonLikePort: ToggleWebtoonLikePort,
    private val readWebtoonLikePort: ReadWebtoonLikePort,
    private val readWebtoonProductPort: ReadWebtoonProductPort,
): ToggleWebtoonLikeUseCase {
    override fun likeWebtoon(user: User, webtoonId: Long) {
        val webtoon = getWebtoonProduct(webtoonId)
        val webtoonLike = getWebtoonLike(user, webtoonId, webtoon)
        webtoonLike.like()
        toggleWebtoonLikePort.toggleWebtoonLike(webtoonLike)
    }

    override fun unlikeWebtoon(user: User, webtoonId: Long) {
        val webtoon = getWebtoonProduct(webtoonId)
        val webtoonLike = getWebtoonLike(user, webtoonId, webtoon)
        webtoonLike.unlike()
        toggleWebtoonLikePort.toggleWebtoonLike(webtoonLike)
    }

    private fun getWebtoonProduct(webtoonId: Long): WebtoonProduct {
        return readWebtoonProductPort.findWebtoonProductById(webtoonId)
            ?: throw IllegalArgumentException("Webtoon with id $webtoonId does not exist.")
    }

    private fun getWebtoonLike(
        user: User,
        webtoonId: Long,
        webtoon: WebtoonProduct
    ): UserLikedWebtoon {
        return readWebtoonLikePort.getWebtoonLike(
            query = UserLikedWebtoonQuery(
                userId = user.id,
                webtoonId = webtoonId
            )
        ) ?: run {
            UserLikedWebtoonFactory.create(
                user = user,
                webtoon = webtoon,
            )
        }
    }
}
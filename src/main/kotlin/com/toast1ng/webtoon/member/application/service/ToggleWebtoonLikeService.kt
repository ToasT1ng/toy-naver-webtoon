package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.ToggleWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import com.toast1ng.webtoon.member.domain.UserLikedWebtoonFactory
import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import org.springframework.stereotype.Service

@Service
class ToggleWebtoonLikeService(
    private val toggleWebtoonLikePort: ToggleWebtoonLikePort,
    private val readWebtoonLikePort: ReadWebtoonLikePort,
    private val readWebtoonProductPort: ReadWebtoonProductPort,
): ToggleWebtoonLikeUseCase {
    override fun likeWebtoon(user: User, webtoonId: Long) {
        toggleLike(user, webtoonId, WebtoonLikedStatus.LIKED)
    }

    override fun unlikeWebtoon(user: User, webtoonId: Long) {
        toggleLike(user, webtoonId, WebtoonLikedStatus.UNLIKED)
    }

    private fun toggleLike(user: User, webtoonId: Long, targetStatus: WebtoonLikedStatus) {
        val webtoon = findWebtoonOrThrow(webtoonId)
        val userLike = getOrCreateUserLike(user, webtoon)

        if (userLike.isAlreadyExits() && userLike.status == targetStatus) {
            val action = targetStatus.name.lowercase()
            throw IllegalStateException("Webtoon with id $webtoonId is already $action by user ${user.username}.")
        }

        when (targetStatus) {
            WebtoonLikedStatus.LIKED -> userLike.like()
            WebtoonLikedStatus.UNLIKED -> userLike.unlike()
        }

        toggleWebtoonLikePort.toggleWebtoonLike(userLike)
    }

    private fun findWebtoonOrThrow(webtoonId: Long): WebtoonProduct {
        return readWebtoonProductPort.findWebtoonProductById(webtoonId)
            ?: throw IllegalArgumentException("Webtoon with id $webtoonId does not exist.")
    }

    private fun getOrCreateUserLike(
        user: User,
        webtoon: WebtoonProduct
    ): UserLikedWebtoon {
        return readWebtoonLikePort.getWebtoonLike(
            query = UserLikedWebtoonQuery(
                userId = user.id,
                webtoonId = webtoon.id
            )
        ) ?: run {
            UserLikedWebtoonFactory.create(
                user = user,
                webtoon = webtoon,
            )
        }
    }
}
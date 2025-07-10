package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.ToggleWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.out.ChangeWebtoonLikeCountPort
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
import org.springframework.transaction.annotation.Transactional

//TODO transactional 통합 테스트 작성 필요
@Transactional
@Service
class ToggleWebtoonLikeService(
    private val toggleWebtoonLikePort: ToggleWebtoonLikePort,
    private val changeWebtoonLikeCountPort: ChangeWebtoonLikeCountPort,
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
        validateUserLikeInput(userLike, targetStatus)
        updateWebtoonLikeCount(webtoon, targetStatus)
        toggleUserLikedWebtoon(userLike, targetStatus)
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

    private fun validateUserLikeInput(
        userLike: UserLikedWebtoon,
        targetStatus: WebtoonLikedStatus,
    ) {
        if (userLike.isAlreadyExits() && userLike.status == targetStatus) {
            val action = targetStatus.name.lowercase()
            throw IllegalStateException("Webtoon is already $action by user.")
        }
    }

    private fun updateWebtoonLikeCount(
        webtoon: WebtoonProduct,
        targetStatus: WebtoonLikedStatus
    ) {
        when (targetStatus) {
            WebtoonLikedStatus.LIKED -> changeWebtoonLikeCountPort.addLikeCount(webtoon)
            WebtoonLikedStatus.UNLIKED -> changeWebtoonLikeCountPort.minusLikeCount(webtoon)
            else -> {}
        }
    }

    private fun toggleUserLikedWebtoon(
        userLike: UserLikedWebtoon,
        targetStatus: WebtoonLikedStatus
    ) {
        userLike.toggle(targetStatus)
        toggleWebtoonLikePort.toggleWebtoonLike(userLike)
    }
}
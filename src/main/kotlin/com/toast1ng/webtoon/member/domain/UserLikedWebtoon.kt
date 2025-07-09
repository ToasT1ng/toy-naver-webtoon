package com.toast1ng.webtoon.member.domain

import com.toast1ng.webtoon.product.domain.WebtoonProduct
import java.time.LocalDateTime

data class UserLikedWebtoon(
    val id: Long = 0L,
    val user: User,
    val webtoon: WebtoonProduct,
    var status: WebtoonLikedStatus,
    var updatedAt: LocalDateTime
) {
    fun like() {
        this.status = WebtoonLikedStatus.LIKED
        this.updatedAt = LocalDateTime.now()
    }

    fun unlike() {
        this.status = WebtoonLikedStatus.UNLIKED
        this.updatedAt = LocalDateTime.now()
    }

    fun isAlreadyExits(): Boolean {
        return this.id != 0L
    }
}

object UserLikedWebtoonFactory {
    fun create(user: User, webtoon: WebtoonProduct): UserLikedWebtoon {
        return UserLikedWebtoon(
            user = user,
            webtoon = webtoon,
            status = WebtoonLikedStatus.LIKED,
            updatedAt = LocalDateTime.now()
        )
    }
}


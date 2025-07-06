package com.toast1ng.webtoon.member.domain

import java.time.LocalDateTime

data class UserLikedWebtoon(
    val userId: Long,
    val webtoonId: Long,
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
}

object UserLikedWebtoonFactory {
    fun create(userId: Long, webtoonId: Long): UserLikedWebtoon {
        return UserLikedWebtoon(
            userId = userId,
            webtoonId = webtoonId,
            status = WebtoonLikedStatus.LIKED,
            updatedAt = LocalDateTime.now()
        )
    }
}


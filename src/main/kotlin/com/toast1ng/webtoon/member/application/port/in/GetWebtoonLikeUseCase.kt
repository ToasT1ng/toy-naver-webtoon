package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

interface GetWebtoonLikeUseCase {
    fun getUserLikedWebtoons(userId: Long): List<UserLikedWebtoon>
    fun getUserLikedWebtoon(userId: Long, webtoonId: Long): UserLikedWebtoon
}
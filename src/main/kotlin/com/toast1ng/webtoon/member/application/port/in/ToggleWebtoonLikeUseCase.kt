package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.User

interface ToggleWebtoonLikeUseCase {
    fun likeWebtoon(user: User, webtoonId: Long)
    fun unlikeWebtoon(user: User, webtoonId: Long)
}
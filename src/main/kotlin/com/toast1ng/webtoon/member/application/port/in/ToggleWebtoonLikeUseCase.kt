package com.toast1ng.webtoon.member.application.port.`in`

import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus

interface ToggleWebtoonLikeUseCase {
    fun toggleLike(user: User, webtoonId: Long, targetStatus: WebtoonLikedStatus)
}
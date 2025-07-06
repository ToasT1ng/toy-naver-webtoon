package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

interface ToggleWebtoonLikePort {
    fun toggleWebtoonLike(userLikedWebtoon: UserLikedWebtoon): UserLikedWebtoon
}
package com.toast1ng.webtoon.member.application.port.`in`

interface ToggleWebtoonLikeUseCase {
    fun likeWebtoon(webtoonId: Long, userId: Long)
    fun unlikeWebtoon(webtoonId: Long, userId: Long)
}
package com.toast1ng.webtoon.member.adapter.`in`.web.request

import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus

enum class WebtoonLikedRequestStatus {
    LIKE,
    UNLIKE,
    NOT_INTERESTED;
}

fun WebtoonLikedRequestStatus.toDomainStatus(): WebtoonLikedStatus {
    return when (this) {
        WebtoonLikedRequestStatus.LIKE -> WebtoonLikedStatus.LIKED
        WebtoonLikedRequestStatus.UNLIKE -> WebtoonLikedStatus.UNLIKED
        WebtoonLikedRequestStatus.NOT_INTERESTED -> WebtoonLikedStatus.NOT_INTERESTED
    }
}
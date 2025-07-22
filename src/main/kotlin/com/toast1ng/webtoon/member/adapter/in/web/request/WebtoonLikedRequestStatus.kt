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

fun WebtoonLikedStatus.toWebtoonLikedRequestStatus(): WebtoonLikedRequestStatus {
    return when (this) {
        WebtoonLikedStatus.LIKED -> WebtoonLikedRequestStatus.LIKE
        WebtoonLikedStatus.UNLIKED -> WebtoonLikedRequestStatus.UNLIKE
        WebtoonLikedStatus.NOT_INTERESTED -> WebtoonLikedRequestStatus.NOT_INTERESTED
    }
}
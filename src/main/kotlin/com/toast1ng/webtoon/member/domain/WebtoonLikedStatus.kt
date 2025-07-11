package com.toast1ng.webtoon.member.domain

enum class WebtoonLikedStatus {
    LIKED, UNLIKED, NOT_INTERESTED
}

fun WebtoonLikedStatus.toQueryString(): String = this.name
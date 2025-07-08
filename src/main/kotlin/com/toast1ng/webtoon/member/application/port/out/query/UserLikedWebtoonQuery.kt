package com.toast1ng.webtoon.member.application.port.out.query

data class UserLikedWebtoonQuery(
    val userId: Long? = null,
    val webtoonId: Long? = null,
    val status: String? = null,
)

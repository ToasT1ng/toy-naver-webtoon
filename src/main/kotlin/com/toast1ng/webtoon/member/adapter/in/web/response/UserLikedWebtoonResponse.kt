package com.toast1ng.webtoon.member.adapter.`in`.web.response

import com.toast1ng.webtoon.common.utils.toResponseTimestamp
import com.toast1ng.webtoon.member.adapter.`in`.web.request.WebtoonLikedRequestStatus
import com.toast1ng.webtoon.member.adapter.`in`.web.request.toWebtoonLikedRequestStatus
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

data class UserLikedWebtoonResponse(
    val webtoonId: Long,
    val title: String,
    val thumbnailUrl: String,
    val likedAt: Long,
    val status: WebtoonLikedRequestStatus,
)

fun UserLikedWebtoon.toResponse(): UserLikedWebtoonResponse {
    return UserLikedWebtoonResponse(
        webtoonId = this.webtoon.id,
        title = this.webtoon.title,
        thumbnailUrl = this.webtoon.mainThumbnail,
        likedAt = this.updatedAt.toResponseTimestamp(),
        status = this.status.toWebtoonLikedRequestStatus()
    )
}
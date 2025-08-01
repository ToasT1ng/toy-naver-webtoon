package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.common.utils.toResponseTimestamp
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

data class WebtoonEpisodeCommentResponse(
    val id: Long,
    val parentId: Long,
    val episodeId: Long,
    val user: WebtoonEpisodeCommentUserResponse,
    var content: String,
    val createdAt: Long,
    val updatedAt: Long
) {
    data class WebtoonEpisodeCommentUserResponse(
        val id: Long,
        val username: String,
        val profileImageUrl: String?
    )
}

fun WebtoonEpisodeComment.toResponse(): WebtoonEpisodeCommentResponse {
    return WebtoonEpisodeCommentResponse(
        id = this.id,
        parentId = this.parentId,
        episodeId = this.episodeId,
        user = WebtoonEpisodeCommentResponse.WebtoonEpisodeCommentUserResponse(
            id = this.user.id,
            username = this.user.username,
            profileImageUrl = this.user.profileImage
        ),
        content = this.content,
        createdAt = this.createdAt.toResponseTimestamp(),
        updatedAt = this.updatedAt.toResponseTimestamp()
    )
}
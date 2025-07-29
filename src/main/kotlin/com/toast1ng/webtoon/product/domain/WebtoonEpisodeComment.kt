package com.toast1ng.webtoon.product.domain

import com.toast1ng.webtoon.member.domain.User
import java.time.LocalDateTime

data class WebtoonEpisodeComment(
    val id: Long = 0L,
    val parentId: Long = 0L,
    val episodeId: Long,
    val user: User,
    var content: String,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
    fun updateContent(newContent: String) {
        this.content = newContent
        this.updatedAt = LocalDateTime.now()
    }
}

object WebtoonEpisodeCommentFactory {
    fun create(
        parentId: Long = 0L,
        episodeId: Long,
        user: User,
        content: String
    ): WebtoonEpisodeComment {
        return WebtoonEpisodeComment(
            parentId = parentId,
            episodeId = episodeId,
            user = user,
            content = content,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}

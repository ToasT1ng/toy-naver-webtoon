package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.member.domain.User

data class MakeWebtoonEpisodeCommentCommand(
    val parentId: Long? = null,
    val episodeId: Long,
    val content: String,
    val user: User
)
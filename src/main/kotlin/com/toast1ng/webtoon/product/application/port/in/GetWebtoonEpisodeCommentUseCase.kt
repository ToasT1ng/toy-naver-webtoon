package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

interface GetWebtoonEpisodeCommentUseCase {
    fun getTopCommentsByEpisodeId(episodeId: Long): List<WebtoonEpisodeComment>
    fun getChildCommentsByParentId(parentId: Long): List<WebtoonEpisodeComment>
}
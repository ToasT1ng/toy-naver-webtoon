package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

interface ReadWebtoonEpisodeCommentPort {
    fun findTopCommentsByEpisodeId(episodeId: Long): List<WebtoonEpisodeComment>
    fun findChildCommentsByParentId(parentId: Long): List<WebtoonEpisodeComment>
}
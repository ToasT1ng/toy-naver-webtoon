package com.toast1ng.webtoon.product.application.port.out.query

data class WebtoonEpisodeCommentQuery(
    val webtoonEpisodeId: Long? = null,
    val parentId: Long? = null,
    val userId: Long? = null,
)
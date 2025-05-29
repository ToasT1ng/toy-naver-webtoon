package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation

data class WebtoonNavigationResponse(
    val previousEpisodeId: Long,
    val currentEpisodeId: Long,
    val nextEpisodeId: Long,
)

fun WebtoonEpisodeNavigation.toResponse(): WebtoonNavigationResponse {
    return WebtoonNavigationResponse(
        previousEpisodeId = this.previousEpisodeId ?: 0L,
        currentEpisodeId = this.currentEpisodeId,
        nextEpisodeId = this.nextEpisodeId ?: 0L,
    )
}
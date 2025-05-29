package com.toast1ng.webtoon.product.domain

data class WebtoonEpisodeNavigation(
    val previousEpisodeId: Long?,
    val currentEpisodeId: Long,
    val nextEpisodeId: Long?,
)
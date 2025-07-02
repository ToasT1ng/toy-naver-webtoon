package com.toast1ng.webtoon.product.application.port.out

interface UpdateEpisodeViewsPort {
    fun increaseEpisodeViews(episodeId: Long)
    fun increaseWebtoonViews(webtoonId: Long)
}
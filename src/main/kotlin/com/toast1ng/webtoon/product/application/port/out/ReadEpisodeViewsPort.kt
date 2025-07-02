package com.toast1ng.webtoon.product.application.port.out

interface ReadEpisodeViewsPort {
    fun getWebtoonRanking(webtoonId: Long, baseHour: Int): List<Pair<Long, Int>>
    fun getEpisodeRanking(episodeId: Long, baseHour: Int): List<Pair<Long, Int>>
}
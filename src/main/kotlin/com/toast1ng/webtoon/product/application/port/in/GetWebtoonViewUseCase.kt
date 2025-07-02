package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.domain.EpisodeView
import com.toast1ng.webtoon.product.domain.WebtoonView

interface GetWebtoonViewUseCase {
    fun getWebtoonRanking(webtoonId: Long): List<WebtoonView>
    fun getEpisodeRanking(episodeId: Long): List<EpisodeView>
}
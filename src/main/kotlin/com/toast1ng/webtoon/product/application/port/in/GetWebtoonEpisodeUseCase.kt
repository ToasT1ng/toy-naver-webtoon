package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page

interface GetWebtoonEpisodeUseCase {
    fun getWebtoonEpisodes(command: GetPagingWebtoonEpisodesCommand): Page<WebtoonEpisode>
}
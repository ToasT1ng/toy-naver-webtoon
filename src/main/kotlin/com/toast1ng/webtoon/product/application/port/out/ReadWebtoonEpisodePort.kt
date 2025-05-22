package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReadWebtoonEpisodePort {
    fun getWebtoonEpisode(query: WebtoonEpisodeQuery): WebtoonEpisode?
    fun getPagingWebtoonEpisodes(query: WebtoonEpisodeQuery, pageable: Pageable): Page<WebtoonEpisode>
}
package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodePagingQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page

interface ReadWebtoonEpisodePort {
    fun getWebtoonEpisode(query: WebtoonEpisodeQuery): WebtoonEpisode?
    fun getPagingWebtoonEpisodes(query: WebtoonEpisodePagingQuery): Page<WebtoonEpisode>
}
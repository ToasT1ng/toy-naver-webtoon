package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeNavigationQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation

interface ReadWebtoonEpisodeNavigationPort {
    fun getWebtoonEpisodeNavigation(query: WebtoonEpisodeNavigationQuery): WebtoonEpisodeNavigation
}
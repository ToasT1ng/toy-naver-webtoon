package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeNavigationCommand
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation

interface GetWebtoonEpisodeNavigationUseCase {
    fun getWebtoonEpisodeNavigation(command: GetWebtoonEpisodeNavigationCommand): WebtoonEpisodeNavigation
}

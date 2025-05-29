package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeNavigationUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeNavigationCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.toQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodeNavigationPort
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation
import org.springframework.stereotype.Service

@Service
class WebtoonEpisodeNavigationService(
    private val readWebtoonEpisodeNavigationPort: ReadWebtoonEpisodeNavigationPort,
) : GetWebtoonEpisodeNavigationUseCase {
    override fun getWebtoonEpisodeNavigation(
        command: GetWebtoonEpisodeNavigationCommand
    ): WebtoonEpisodeNavigation {
        return readWebtoonEpisodeNavigationPort.getWebtoonEpisodeNavigation(command.toQuery())
    }
}


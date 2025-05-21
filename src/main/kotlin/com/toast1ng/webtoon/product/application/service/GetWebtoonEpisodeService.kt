package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonEpisodeQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodePort
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class GetWebtoonEpisodeService(
    private val readWebtoonEpisodePort: ReadWebtoonEpisodePort,
) : GetWebtoonEpisodeUseCase {
    override fun getWebtoonEpisodes(
        command: GetPagingWebtoonEpisodesCommand
    ): Page<WebtoonEpisode> {
        val query = GetWebtoonEpisodeQuery(
            webtoonId = command.webtoonId,
        )
        return readWebtoonEpisodePort.getPagingWebtoonEpisodes(query, PageRequest.of(command.pageNo, command.pageSize))
    }
}
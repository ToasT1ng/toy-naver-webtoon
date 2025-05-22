package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeCommand
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodePort
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LiveWebtoonEpisodeReadService(
    private val readWebtoonEpisodePort: ReadWebtoonEpisodePort,
) : GetWebtoonEpisodeUseCase {
    override fun getWebtoonEpisode(command: GetWebtoonEpisodeCommand): WebtoonEpisode {
        val query = WebtoonEpisodeQuery(
            id = command.id,
        )
        return requireNotNull(readWebtoonEpisodePort.getWebtoonEpisode(query))
    }

    override fun getWebtoonEpisodes(
        command: GetPagingWebtoonEpisodesCommand
    ): Page<WebtoonEpisode> {
        val query = WebtoonEpisodeQuery(
            webtoonId = command.webtoonId,
            uploadDateTo = LocalDateTime.now(),
        )
        return readWebtoonEpisodePort.getPagingWebtoonEpisodes(query, PageRequest.of(command.pageNo, command.pageSize))
    }
}
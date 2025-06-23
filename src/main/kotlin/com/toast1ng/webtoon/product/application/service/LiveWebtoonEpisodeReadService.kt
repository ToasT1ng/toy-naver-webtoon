package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.toQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodePort
import com.toast1ng.webtoon.product.application.port.out.query.toPagingQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class LiveWebtoonEpisodeReadService(
    private val readWebtoonEpisodePort: ReadWebtoonEpisodePort,
) : GetWebtoonEpisodeUseCase {
    override fun getWebtoonEpisode(command: GetWebtoonEpisodeCommand): WebtoonEpisode {
        return checkNotNull(readWebtoonEpisodePort.getWebtoonEpisode(command.toQuery()))
    }

    override fun getWebtoonEpisodes(
        command: GetPagingWebtoonEpisodesCommand
    ): Page<WebtoonEpisode> {
        return readWebtoonEpisodePort.getPagingWebtoonEpisodes(
            query = command.toPagingQuery(uploadDateTo = LocalDateTime.now())
        )
    }
}
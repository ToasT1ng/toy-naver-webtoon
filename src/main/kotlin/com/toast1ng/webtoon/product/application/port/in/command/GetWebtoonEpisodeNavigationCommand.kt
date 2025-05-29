package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeNavigationQuery
import java.time.LocalDateTime

data class GetWebtoonEpisodeNavigationCommand (
    val webtoonId: Long,
    val episodeId: Long
)

fun GetWebtoonEpisodeNavigationCommand.toQuery(): WebtoonEpisodeNavigationQuery {
    return WebtoonEpisodeNavigationQuery(
        webtoonId = this.webtoonId,
        currentEpisodeId = this.episodeId,
        uploadDateTo = LocalDateTime.now()
    )
}
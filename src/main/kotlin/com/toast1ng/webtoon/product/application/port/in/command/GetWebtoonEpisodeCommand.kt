package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery

data class GetWebtoonEpisodeCommand(
    val webtoonId: Long,
    val id: Long,
)

fun GetWebtoonEpisodeCommand.toQuery(): WebtoonEpisodeQuery {
    return WebtoonEpisodeQuery(
        id = this.id,
        webtoonId = this.webtoonId
    )
}
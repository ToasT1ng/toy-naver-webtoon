package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.common.utils.toDateOnlyFormatString
import com.toast1ng.webtoon.product.domain.WebtoonEpisode

data class WebtoonEpisodeResponse(
    val episodeId: Long,
    val title: String,
    val thumbnail: String,
    val rating: String,
    val uploadDate: String,
)

fun WebtoonEpisode.toResponse(): WebtoonEpisodeResponse {
    return WebtoonEpisodeResponse(
        episodeId = this.id,
        title = this.title,
        thumbnail = this.thumbnail,
        rating = this.rating.toString(),
        uploadDate = this.uploadDate.toDateOnlyFormatString(),
    )
}

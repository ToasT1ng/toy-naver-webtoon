package com.toast1ng.webtoon.product.adapter.`in`.web.response

import com.toast1ng.webtoon.common.utils.toDateOnlyFormatWithHyphenString
import com.toast1ng.webtoon.product.domain.WebtoonEpisode

data class WebtoonEpisodeResponse(
    val webtoonTitle: String,
    val webtoonId: Long,
    val episodeId: Long,
    val title: String,
    val thumbnail: String,
    val rating: String,
    val uploadDate: String,
    val images: List<String>,
)

fun WebtoonEpisode.toResponse(): WebtoonEpisodeResponse {
    return WebtoonEpisodeResponse(
        webtoonTitle = this.webtoon.title,
        webtoonId = this.webtoon.id,
        episodeId = this.id,
        title = this.title,
        thumbnail = this.thumbnail,
        rating = this.rating.toString(),
        uploadDate = this.uploadDate.toDateOnlyFormatWithHyphenString(),
        images = this.images
    )
}

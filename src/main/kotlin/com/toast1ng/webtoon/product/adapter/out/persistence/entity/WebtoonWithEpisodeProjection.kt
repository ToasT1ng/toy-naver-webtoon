package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import com.querydsl.core.annotations.QueryProjection
import java.math.BigDecimal
import java.time.LocalDateTime

data class WebtoonWithEpisodeProjection @QueryProjection constructor(
    val webtoonId: Long,
    val title: String,
    val description: String,
    val day: String,
    val status: String,
    val restrictAge: Int,
    val rating: BigDecimal,
    val likes: Int,
    val views: Int,
    val mainThumbnail: String?,
    val subThumbnail: String?,
    val genre: GenreJpaEntity,
    val episodeId: Long?,
    val episodeTitle: String?,
    val episodeNumber: Int?,
    val episodeUploadAt: LocalDateTime?,
)
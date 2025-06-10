package com.toast1ng.webtoon.product.application.port.`in`.command

data class UpdateWebtoonViewsCommand(
    val userId: Long? = null,
    val episodeId: Long,
    val webtoonId: Long
)
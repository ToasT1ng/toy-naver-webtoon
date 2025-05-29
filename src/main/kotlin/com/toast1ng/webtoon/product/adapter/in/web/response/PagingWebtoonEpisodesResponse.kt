package com.toast1ng.webtoon.product.adapter.`in`.web.response

data class PagingWebtoonEpisodesResponse(
    val webtoonId: Long,
    val pageNo: Int,
    val pageSize: Int,
    val totalCount: Long,
    val totalPage: Int,
    val isLastPage: Boolean,
    val content: List<WebtoonEpisodeResponse>,
)
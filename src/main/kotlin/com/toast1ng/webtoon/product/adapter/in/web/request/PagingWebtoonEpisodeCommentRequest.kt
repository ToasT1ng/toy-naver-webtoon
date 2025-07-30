package com.toast1ng.webtoon.product.adapter.`in`.web.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class PagingWebtoonEpisodeCommentRequest(
    @Min(1)
    val pageNo: Int,
    @Max(30)
    val pageSize: Int,
)
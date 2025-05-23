package com.toast1ng.webtoon.product.adapter.`in`.web.request

import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class PagingWebtoonEpisodesRequest(
    @Min(0)
    val pageNo: Int,
    @Max(30)
    val pageSize: Int,
)

fun PagingWebtoonEpisodesRequest.toCommand(webtoonId: Long) = GetPagingWebtoonEpisodesCommand(
    pageNo = this.pageNo,
    pageSize = this.pageSize,
    webtoonId = webtoonId,
)
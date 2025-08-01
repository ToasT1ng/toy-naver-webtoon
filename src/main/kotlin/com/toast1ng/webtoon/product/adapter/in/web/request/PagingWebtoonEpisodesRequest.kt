package com.toast1ng.webtoon.product.adapter.`in`.web.request

import com.toast1ng.webtoon.common.domain.SortDirection
import com.toast1ng.webtoon.common.domain.SortOption
import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.WebtoonEpisodeSortKey
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class PagingWebtoonEpisodesRequest(
    @Min(1)
    val pageNo: Int,
    @Max(30)
    val pageSize: Int,
    val sortDirection: SortDirection = SortDirection.DESC,
)

fun PagingWebtoonEpisodesRequest.toCommand(webtoonId: Long) = GetPagingWebtoonEpisodesCommand(
    pageNo = this.pageNo - 1,
    pageSize = this.pageSize,
    sortOptions = listOf(
        SortOption(
            key = WebtoonEpisodeSortKey.UPLOADED,
            direction = sortDirection
        )
    ),
    webtoonId = webtoonId,
)
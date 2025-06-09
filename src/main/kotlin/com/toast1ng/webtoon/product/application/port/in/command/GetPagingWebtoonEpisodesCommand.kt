package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.common.PagingCommand
import com.toast1ng.webtoon.common.SortDirection
import com.toast1ng.webtoon.common.SortKey
import com.toast1ng.webtoon.common.SortOption

data class GetPagingWebtoonEpisodesCommand(
    override val pageNo: Int,
    override val pageSize: Int,
    override val sortOptions: List<SortOption<WebtoonEpisodeSortKey>> = listOf(
        SortOption(
            key = WebtoonEpisodeSortKey.UPLOADED,
            direction = SortDirection.DESC
        )
    ),
    val webtoonId: Long,
) : PagingCommand<WebtoonEpisodeSortKey>()

enum class WebtoonEpisodeSortKey : SortKey {
    UPLOADED,
}
